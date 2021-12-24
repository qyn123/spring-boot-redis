package com.qiaoyn.juc.unsafelist;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName ListTest
 * @create 2021-12-20 11:15
 **/
public class ListTest {

    public static void main(String[] args) {

        /**
         * ArrayList在并发情况下线程不安全
         *  java.util.ConcurrentModificationException并发修改异常
         * 解决办法：
         * 1)new Vector<>(); 集合操作方法为synchronized修饰的同步方法
         * 2)Collections.synchronizedList(new ArrayList<>());
         * 3)new CopyOnWriteArrayList<>(); 写入时复制
         * 多线程调用的时候，读取的时候固定，写入的时候复制。
         *
         * 4. Vector & ArrayList & CopyOnWriteArrayList的区别？
         * 这三个集合类都继承List接口
         * 1)ArrayList是线程不安全的；
         * 2)Vector是比较古老的线程安全的，但性能不行；
         * 3)CopyOnWriteArrayList在兼顾了线程安全的同时，又提高了并发性，性能比Vector有不少提高
         */
        List<String> list =  new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
