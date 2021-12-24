package com.qiaoyn.juc.unsafelist;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName MapTest
 * @create 2021-12-20 14:07
 **/
public class MapTest {

    public static void main(String[] args) {
        /**
         * Map在并发的时候会出现安全问题：java.util.ConcurrentModificationException 并发修改异常
         * 解决办法：
         * 1)Collections.synchronizedMap(new HashMap<>(16));
         * 2)
         *
         */

        Map<String,Object> map = new ConcurrentHashMap<>(16);
        for (int i = 1; i <= 100; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            }).start();
        }

    }
}
