package com.qiaoyn.juc.singletons;

/**
 * 静态内部类单例模式
 * @author yn.qiao
 * @version 1.0
 * @ClassName SingletonNiMing
 * @create 2021-12-27 14:44
 **/
public class SingletonNiMing {

    private static class SingletonHolder{
        private static SingletonNiMing instance = new SingletonNiMing();
    }

    private SingletonNiMing(){}

    public static SingletonNiMing getInstance(){
        return SingletonHolder.instance;
    }
}
