package com.qiaoyn.juc.volatiled;

import java.util.concurrent.TimeUnit;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName VolatileTest03
 * @create 2021-12-24 15:19
 **/
public class VolatileTest03 {


    public volatile static int num = 0;


    public static void main(String[] args) throws InterruptedException {

        //num = 0则一直处于循环状态
        new Thread(() -> {
            while (num == 0) {

            }
        }).start();
        

        TimeUnit.SECONDS.sleep(2);

        // num = 1没有立即刷新到主内存当中,必需使用 volatile修饰num，当工作内存中数据发生变化后会刷新到主内存当中
        num = num + 1;

        System.out.println(num);

    }
}
