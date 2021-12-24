package com.qiaoyn.juc.volatiled;

import java.util.concurrent.TimeUnit;

/**
 * 保证可见性
 *
 * @author yn.qiao
 * @version 1.0
 * @ClassName VolatileTest
 * @create 2021-12-23 15:56
 **/
public class VolatileTest {

    /**
     * volatile:保证可见性
     * 不加volatile程序就会死循环
     * 加了volatile可以保证可见性
     */
    public volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        //线程1对主内存的变化不可知
        new Thread(() -> {
            while (num == 0) {

            }
        }).start();

        //让线程处于休眠2s
        TimeUnit.SECONDS.sleep(2);
        //修改主内存的num值
        num = num + 1;

        System.out.println(num);

    }
}
