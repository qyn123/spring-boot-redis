package com.qiaoyn.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName CasDemo
 * @create 2021-12-30 16:56
 **/
public class CasDemo {

    public static void main(String[] args) {

        //compareAndSet:比较并交换，如果expect值与初始值相同，则进行更新，返回true；否则不更新，返回false
        AtomicInteger atomicInteger = new AtomicInteger(12);
        boolean b = atomicInteger.compareAndSet(12, 13);
        System.out.println(b);
        System.out.println(atomicInteger.get());

        System.out.println("==============================================");
        boolean c = atomicInteger.compareAndSet(12, 14);
        int andIncrement = atomicInteger.getAndIncrement();
        System.out.println(c);
        System.out.println(andIncrement);

    }
}
