package com.qiaoyn.juc.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName C
 * @create 2021-12-17 16:55
 * Condition.signal()实现精准唤醒
 * number = 1 ,输出A
 * number = 2 ,输出B
 * number = 3 ,输出C
 * 交替执行
 **/
public class C {

    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                data.printA();
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                data.printB();
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                data.printC();
            }
        },"CC").start();
    }
}

class Data3{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();



    public void printA(){
        lock.lock();
        try{
           while (number != 1) {
               conditionA.await();
           }
           number = 2;
            System.out.println(Thread.currentThread().getName() + "AAAAA");
           conditionB.signal();
        }catch (Exception exception){
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {
            while (number != 2){
                conditionB.await();
            }
            number = 3;
            System.out.println(Thread.currentThread().getName() + "BBBBB");
            conditionC.signal();
        }catch (Exception exception){
            exception.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            while (number != 3){
                conditionC.await();
            }
            number = 1;
            System.out.println(Thread.currentThread().getName() + "CCCCC");
            conditionA.signal();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
