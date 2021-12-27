package com.qiaoyn.juc.singletons;

/**
 * 懒汉式单例模式：
 * @author yn.qiao
 * @version 1.0
 * @ClassName LazySingleton
 * @create 2021-12-27 14:15
 **/
public class LazySingleton {

    private LazySingleton(){}

    private static LazySingleton instance;

    /**
     * 加锁，一次只能让一个对象访问
     */
    public static synchronized LazySingleton getInstance(){
        //如果还没有被实例化过，就实例化一个，然后返回
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }

}
