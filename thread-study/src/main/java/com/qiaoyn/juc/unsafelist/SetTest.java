package com.qiaoyn.juc.unsafelist;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName SetTest
 * @create 2021-12-20 13:47
 **/
public class SetTest {

    public static void main(String[] args) {
        /**
         * set在并发情况下不安全：会出现并发修改异常java.util.ConcurrentModificationException
         * 解决办法：
         *1)Collections.synchronizedSet(new HashSet<>())
         *2)new CopyOnWriteArraySet<>();写入时复制
         *
         */

        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
