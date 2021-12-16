package com.qiaoyn.juc;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName ThreadDemo01
 * @create 2021-12-16 17:10
 **/
public class ThreadDemo01 {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 1; i < 30; i++) {
                ticket.ticketSale();
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 1; i < 30; i++) {
                ticket.ticketSale();
            }
        },"B").start();



        new Thread(() -> {
            for (int i = 1; i < 30; i++) {
                ticket.ticketSale();
            }
        },"C").start();

    }
}

class Ticket{

    private int ticketNum = 20;

    public synchronized void ticketSale(){
        if(ticketNum > 0){
            System.out.println(Thread.currentThread().getName() +"卖出了第"+ (ticketNum--) +"张票"+"还剩" +ticketNum +"张票");
        }
    }
}
