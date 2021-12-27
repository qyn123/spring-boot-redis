package com.qiaoyn.juc.singletons;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName SingletonTest
 * @create 2021-12-27 14:25
 **/
public class SingletonTest {

    public static void main(String[] args) {
            for (int i = 0; i < 5; i++) {
                new Thread(() -> {
                    System.out.println(LazySingleton.getInstance().hashCode());
                }).start();
            }

    }
}
