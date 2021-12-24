package com.qiaoyn.juc.count;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量：限流
 * @author yn.qiao
 * @version 1.0
 * @ClassName SemaphoreTest
 * @create 2021-12-20 15:14
 **/
public class SemaphoreTest {
    /**
     * Semaphore：信号量
     * semaphore.acquire() 获得，假设已经满了，等待，等待被释放为止。
     *  semaphore.release() 释放，会将当前的信号量释放+1，然后唤醒等待的线程
     */

    public static void main(String[] args) {
        //线程数量，限流
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
