package com.qiaoyn.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName ThreadDemo02
 * @create 2021-12-16 17:21
 * ReentrantLock 默认非公平锁，允许插队。
 **/
public class ThreadDemo02 {

    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();

        new Thread(() -> {for (int i = 1; i < 30; i++){ticket.ticketSale();}},"A").start();
        new Thread(() -> {for (int i = 1; i < 30; i++){ticket.ticketSale();}},"B").start();
        new Thread(() -> {for (int i = 1; i < 30; i++){ticket.ticketSale();}},"C").start();
    }
}

class Ticket2{

    private int ticketNum = 20;

    //创建锁
    Lock lock = new ReentrantLock();

    public  void ticketSale(){
        //加锁
        lock.lock();
        try {
            if(ticketNum > 0){
                System.out.println(Thread.currentThread().getName() +"卖出了第"+ (ticketNum--) +"张票"+"还剩" +ticketNum +"张票");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放锁
           lock.unlock();
        }

    }
}
