package com.qiaoyn.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName ThreadDemo01
 * @create 2021-12-14 15:10
 **/
public class ThreadDemo01 {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            };
            thread.start();
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - start);
        System.out.println(list.size());
    }
}
