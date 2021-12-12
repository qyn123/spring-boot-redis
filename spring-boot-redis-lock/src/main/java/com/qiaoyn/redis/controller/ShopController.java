package com.qiaoyn.redis.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName ShopController
 * @create 2021-12-11 14:47
 **/
@RestController
public class ShopController {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    Redisson redisson;

    @RequestMapping("/set")
    public void test() {
        redisTemplate.opsForValue().set("shop", 200);
    }

    /**
     * 分布式条件下并发问题，synchronized无法锁住
     **/
/*    @RequestMapping("/get")
    public String getEnd(){
        synchronized (this){
            Integer shop = (Integer) redisTemplate.opsForValue().get("shop");
            if(shop > 0){
                int realShop = shop - 1;
                redisTemplate.opsForValue().set("shop",realShop);
                System.out.println("剩余库存商品为"+ realShop);
            } else {
                System.out.println("库存不足");
            }
        }
        return "end";
    }*/

    /**
     * 入门级别分布式锁
     */
   /* @RequestMapping("/get")
    public String getEnd() {
        String lockKey = "simple_lock_key";
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(lockKey, "redis_lock");
        if(!lock){
            return "系统繁忙,请稍后操作";
        }
        Integer shop = (Integer) redisTemplate.opsForValue().get("shop");
        if (shop > 0) {
            int realShop = shop - 1;
            redisTemplate.opsForValue().set("shop", realShop);
            System.out.println("剩余库存商品为" + realShop);
        } else {
            System.out.println("库存不足");
        }
        redisTemplate.delete(lockKey);

        return "end";
    }
*/
    /**
     * 1.如果在运行过程中抛异常，则锁不会被释放，此时高并发条件下又会出现多个用户拿到同一个商品，可用try catch finally
     * 2.如果在运行过程中，系统宕机，此时也不会释放锁，又会出现多个用户拿到同一个商品，可以设置一个过期时间。
     * 3.设置锁和过期时间之间存在原子性问题:setIfAbsent(K var1, V var2, long var3, TimeUnit var5)
     * 4.高并发情况下存在锁永久失效问题
     **/
   /* @RequestMapping("/get")
    public String getEnd() {
        String lockKey = "simple_lock_key";
        String clientId = UUID.randomUUID().toString();
        try {
            //设置过期时间为10s
            *//*Boolean lock = redisTemplate.opsForValue().setIfAbsent(lockKey, "redis_lock");
            redisTemplate.expire(lockKey,10, TimeUnit.SECONDS);*//*
            Boolean lock = redisTemplate.opsForValue().setIfAbsent(lockKey, clientId,10,TimeUnit.SECONDS);
            if(!lock){
                return "系统繁忙,请稍后操作";
            }
            Integer shop = (Integer) redisTemplate.opsForValue().get("shop");
            if (shop > 0) {
                int realShop = shop - 1;
                redisTemplate.opsForValue().set("shop", realShop);
                System.out.println("剩余库存商品为" + realShop);
            } else {
                System.out.println("库存不足");
            }
        } finally {
            //finally代码块中的最终都会执行，所以这种情况可以避免抛异常导致的锁未被释放掉。
            //如果加的锁和持有的锁相同，在进行删除锁(解决永久失效问题)
            if(clientId.equals(redisTemplate.opsForValue().get(lockKey))){
                redisTemplate.delete(lockKey);
            }
        }
        return "end";
    }*/
    @RequestMapping("/get")
    public String getEnd() {
        //redisson解决分布式锁续命问题
        String lockKey = "simple_lock_key";
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            redissonLock.lock();
            Integer shop = (Integer) redisTemplate.opsForValue().get("shop");
            if (shop > 0) {
                int realShop = shop - 1;
                redisTemplate.opsForValue().set("shop", realShop);
                System.out.println("剩余库存商品为" + realShop);
            } else {
                System.out.println("库存不足");
            }
        } finally {
            redissonLock.unlock();
        }
        return "end";
    }


}
