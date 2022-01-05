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

}
