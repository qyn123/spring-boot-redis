package com.qiaoyn.juc.count;

import java.util.concurrent.CountDownLatch;

/**
 * 减法计数器
 * @author yn.qiao
 * @version 1.0
 * @ClassName CountDownLatchDemo
 * @create 2021-12-20 14:46
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "go out");
                countDownLatch.countDown(); //每次减1
            },String.valueOf(i)).start();
        }

        //等待归0
        countDownLatch.await();
    }
}
