package com.qiaoyn.juc.singletons;

/**
 * 测试多线程下单例模式
 * @author yn.qiao
 * @version 1.0
 * @ClassName SingletonTest
 * @create 2021-12-27 14:25
 **/
public class SingletonTest {

    public static final int M = 5;

    public static void main(String[] args) {

            for (int i = 1; i <= M; i++) {
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "的hashcode====>" + SingletonNiMing.getInstance().hashCode());
                },"Thread-" + i).start();
            }

    }
}
