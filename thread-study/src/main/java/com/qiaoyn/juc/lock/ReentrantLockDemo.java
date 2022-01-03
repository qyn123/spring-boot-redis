package com.qiaoyn.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * @author yn.qiao
 * @version 1.0
 * @ClassName ReentrantLockDemo
 * @create 2022-01-02 14:51
 **/
public class ReentrantLockDemo {

    public static void main(String[] args) {
        MobilePhone mobilePhone = new MobilePhone();
        for (int i = 1; i < 5; i++) {
            new Thread(()->{
                mobilePhone.call();
            },"线程"+i).start();
        }
    }
}

class MobilePhone{

    private Lock lock = new ReentrantLock();

    public void call(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"-call");
            //同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
            sendMsg();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void sendMsg(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"-sendMsg");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
