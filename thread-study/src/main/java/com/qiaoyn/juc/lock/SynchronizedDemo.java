package com.qiaoyn.juc.lock;

/**
 * 可重入锁
 * @author yn.qiao
 * @version 1.0
 * @ClassName SynchronizedDemo
 * @create 2022-01-02 14:45
 **/
public class SynchronizedDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        for (int i = 1; i < 5; i++) {
            new Thread(()->{
                phone.call();
            },"线程"+ i).start();
        }
    }
}

class Phone{

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName() + "-call");
        //同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
        sendMsg();
    }

    public synchronized void sendMsg(){
        System.out.println(Thread.currentThread().getName() + "-sendMsg");
    }
}
