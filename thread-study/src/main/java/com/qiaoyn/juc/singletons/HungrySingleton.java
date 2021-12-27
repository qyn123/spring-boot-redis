package com.qiaoyn.juc.singletons;

/**
 * 饿汉式单例模式：
 * @author yn.qiao
 * @version 1.0
 * @ClassName HungrySingleton
 * @create 2021-12-27 14:18
 **/
public class HungrySingleton {

    private HungrySingleton(){}

    private static HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(HungrySingleton.getInstance().hashCode());
            }).start();
        }
    }
}
