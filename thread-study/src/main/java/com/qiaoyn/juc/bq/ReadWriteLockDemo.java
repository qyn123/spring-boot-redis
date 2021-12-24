package com.qiaoyn.juc.bq;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：
 * 独占锁：写锁，一次只能被一个线程占有
 * 共享锁：读锁，多个线程可以同时占有。
 * @author yn.qiao
 * @version 1.0
 * @ClassName ReadWriteLockDemo
 * @create 2021-12-22 10:18
 **/
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        MyCacheLock myCache = new MyCacheLock();
        for (int i = 1; i <= 5; i++) {
            final int  temp = i;
        new Thread(()->{
                myCache.put(String.valueOf(temp),temp);
        },String.valueOf(temp)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int  temp = i;
            new Thread(()->{
                myCache.get(String.valueOf(temp));
            },String.valueOf(temp)).start();
        }
    }

}

/**
 * 未加锁的缓存：
 * 读：允许多个线程读
 * 写：只允许一个线程写
 */
class  MyCache{
    private volatile Map<String,Object> map = new HashMap<>(16);

    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName() +"写入"+ key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() +"写入完成");
    }

    public void get(String key){
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取完成" + o);
    }
}


class  MyCacheLock{
    private volatile Map<String,Object> map = new HashMap<>(16);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        //写入锁：只允许一个线程同时写入(写入是一个原子性操作，不允许其他线程插队)
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() +"写入"+ key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() +"写入完成");
        } catch (Exception exception){
            exception.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        //读取锁：允许所有人读
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成" + o);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}
