package com.qiaoyn.thread;

import java.util.concurrent.*;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName ThreadPoolTest
 * @create 2021-12-16 13:39
 **/
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor excutor = new ThreadPoolExecutor(10,20,0L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        for (int i = 1; i <= 100; i++) {
            excutor.execute(new MyTask(i));
        }
    }
}

class Task implements Runnable{

    int i;

    public Task(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "处理了" + i);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
