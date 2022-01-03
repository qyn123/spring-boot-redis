package com.qiaoyn.juc.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName AbaResolve
 * @create 2021-12-31 16:20
 **/
public class AbaResolve {

    static AtomicStampedReference<Integer> ai = new AtomicStampedReference<>(4, 0);

    public static void main(String[] args) {
        new Thread(() -> {
            //四个参数分别是预估内存值，更新值，预估版本号，初始版本号
            //只有当预估内存值==实际内存值相等并且预估版本号==实际版本号，才会进行修改
            boolean b = ai.compareAndSet(4, 5, 0, 1);
            System.out.println(Thread.currentThread().getName() + "是否成功将ai的值修改为5：" + b);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b = ai.compareAndSet(5, 4, 1, 2);
            System.out.println(Thread.currentThread().getName() + "是否成功将ai的值修改为4：" + b);
        }, "A").start();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = ai.compareAndSet(4, 10, 0, 1);
            System.out.println(Thread.currentThread().getName() + "是否成功将ai的值修改为10：" + b);
        }, "B").start();

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println("ai最终的值为：" + ai.getReference());
    }
}
