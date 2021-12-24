package com.qiaoyn.juc.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName B
 * @create 2021-12-17 16:04
 * Lock版的生产者消费者
 **/
public class B {
    public static void main(String[] args) {

        Data2 data = new Data2();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    data.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    data.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    data.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    data.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();

    }
}



class Data2{

    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();



    /**
     * 增加
     */
    public  void increase() throws InterruptedException {
        lock.lock();
        try {
            //不等于0的时候让线程处于等待状态
            while (number != 0){
                condition.await();
            }
            //+1
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" +number);
            //精准唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    /**
     * 减少
     */
    public  void decrease() throws InterruptedException {
        lock.lock();
        try {
            //等于0的时候让线程处于等待状态
            while (number == 0){
                condition.await();
            }
            //-1
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" +number);
            //精准唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}