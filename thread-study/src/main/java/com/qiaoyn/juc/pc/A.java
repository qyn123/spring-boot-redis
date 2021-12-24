package com.qiaoyn.juc.pc;


/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName A
 * @create 2021-12-17 14:29
 * synchronized版本的生产者消费者问题，if条件会出现虚假唤醒
 * 两个线程：
 * A + 1
 * B - 1
 * 交替执行
 * 当有四个线程执行时
 * 数据交替执行会出现严重的问题
 * 根据官方文档介绍。将if改成while,则可以避免该问题产生
 **/
public class A {
    public static void main(String[] args) {

        Data data = new Data();
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



class Data{

    private int number = 0;

    /**
     * 增加
     */
    public synchronized void increase() throws InterruptedException {
        //不等于0的时候让线程处于等待状态
        while (number != 0){
            this.wait();
        }
        //+1
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" +number);
        //精准唤醒
        this.notifyAll();
    }

    /**
     * 减少
     */
    public synchronized void decrease() throws InterruptedException {
        //等于0的时候让线程处于等待状态
        while (number == 0){
            this.wait();
        }
        //-1
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" +number);
        //精准唤醒
        this.notifyAll();
    }

}
