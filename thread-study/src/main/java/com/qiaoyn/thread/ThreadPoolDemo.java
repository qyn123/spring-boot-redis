package com.qiaoyn.thread;

import java.util.concurrent.*;

/**
 * 线程池的比较
 * @author yn.qiao
 * @version 1.0
 * @ClassName ThreadPoolDemo
 * @create 2021-12-14 15:39
 **/
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor(); //最慢
        ExecutorService executorService1 = Executors.newFixedThreadPool(10); // 慢
        ExecutorService executorService2 = Executors.newCachedThreadPool(); //快
        ThreadPoolExecutor executorService3 = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 1; i <= 100; i++) {
            executorService3.execute(new MyTask(i));
        }

    }
}

class MyTask implements Runnable{

    int i = 0;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---"+ i);
        try {
            //休眠1秒
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
