package com.qiaoyn.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName ThreadDemo02
 * @create 2021-12-14 15:17
 **/
public class ThreadDemo02 {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list = new ArrayList<Integer>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100000; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - start);
        System.out.println(list.size());
    }
}
