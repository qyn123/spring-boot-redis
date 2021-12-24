package com.qiaoyn.juc.volatiled;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不保证原子性
 *
 * @author yn.qiao
 * @version 1.0
 * @ClassName VolatileTest02
 * @create 2021-12-23 16:16
 **/
public class VolatileTest02 {

    /**
     * 使用原子类解决原子性问题
     */
    public volatile static AtomicInteger num = new AtomicInteger();


    public static void add() {
        //+1操作
        num.getAndIncrement();
    }

    public static void main(String[] args) {

        //理论上num的值应该是20000
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        //java默认执行的线程main,gc
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        //保证此时执行的线程是main线程
        System.out.println(Thread.currentThread().getName() + "线程执行结果,num=" + num);
    }
}
