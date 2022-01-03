package com.qiaoyn.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName AbaDemo
 * @create 2021-12-31 16:11
 **/
public class AbaDemo {

    //线程操作资源，原子类ai的初始值为4
    static AtomicInteger ai = new AtomicInteger(4);

    public static void main(String[] args) {

        new Thread(() -> {
            //利用CAS将ai的值改成5
            boolean b = ai.compareAndSet(4, 5);
            System.out.println(Thread.currentThread().getName() + "是否成功将ai的值修改为5：" + b);
            //休眠一秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //利用CAS将ai的值改回4
            b = ai.compareAndSet(5, 4);
            System.out.println(Thread.currentThread().getName() + "是否成功将ai的值修改为4：" + b);
        }, "A").start();
        new Thread(() -> {
            //模拟此线程执行较慢的情况
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //利用CAS将ai的值从4改为10
            boolean b = ai.compareAndSet(4, 10);
            System.out.println(Thread.currentThread().getName() + "是否成功将ai的值修改为10：" + b);
        }, "B").start();

        //等待其他线程完成，为什么是2，因为一个是main线程，一个是后台的GC线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println("ai最终的值为：" + ai.get());

    }
}
