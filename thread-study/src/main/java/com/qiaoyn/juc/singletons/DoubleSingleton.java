package com.qiaoyn.juc.singletons;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 双重校验锁 + 原子性检验
 * @author yn.qiao
 * @version 1.0
 * @ClassName DoubleSingleton
 * @create 2021-12-27 14:22
 **/
public class DoubleSingleton {

    private  DoubleSingleton(){}

    private volatile static DoubleSingleton instance;

    public static DoubleSingleton getInstance(){
        if(instance == null){
            synchronized (DoubleSingleton.class){
                if(instance == null){
                    instance = new DoubleSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        DoubleSingleton instance = DoubleSingleton.getInstance();
        DoubleSingleton instance1 = DoubleSingleton.getInstance();
        //通过反射创建实例
        Constructor<DoubleSingleton> declaredConstructor = DoubleSingleton.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        DoubleSingleton instance2 = declaredConstructor.newInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        //反射后单利模式被破坏
        System.out.println(instance1.hashCode() == instance2.hashCode());
    }

}
