package com.qiaoyn.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * @author yn.qiao
 * @version 1.0
 * @ClassName SpinlockDemo
 * @create 2022-01-02 15:01
 **/
public class SpinlockDemo {


    /**
     * 原子引用线程
     */
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 加锁
     */
    public void myLock(){
        System.out.println(Thread.currentThread().getName() + "-myLock");
        //自旋
        while (!atomicReference.compareAndSet(null,Thread.currentThread())){
            //do nothing
        }
    }

    /**
     * 释放锁
     */
    public void myUnLock(){
        atomicReference.compareAndSet(Thread.currentThread(),null);
        System.out.println(Thread.currentThread().getName() + "-myUnLock");
    }


    public static void main(String[] args) {
        SpinlockDemo spinlockDemo = new SpinlockDemo();

        new Thread(()->{
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinlockDemo.myUnLock();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinlockDemo.myUnLock();
        },"B").start();
    }



}
