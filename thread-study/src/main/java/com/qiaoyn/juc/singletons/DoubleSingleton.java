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

    /**
     * volatile：保证可见性，不保证原子性(只能保证对单次读/写的原子性)，由于内存屏障，可以保证避免指令重排的现象产生
     */
    private volatile static DoubleSingleton instance;

    public static DoubleSingleton getInstance(){
        //第一次判断实例是否存在，如果存在，直接返回，不存在
        if(instance == null){
            synchronized (DoubleSingleton.class){
                //如果两个线程到达这块，一个线程拿到锁创建实例，释放锁，
                // 下一个线程在拿到锁，如果不做两次判断，则又会创建一个实例，此时产生两个实例
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
        //通过反射创建实例:通过无参构造创建实例
        Constructor<DoubleSingleton> declaredConstructor = DoubleSingleton.class.getDeclaredConstructor();
        //通过无参构造创建实例
        DoubleSingleton instance2 = declaredConstructor.newInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        //反射后单利模式被破坏
        System.out.println(instance1.hashCode() == instance2.hashCode());
    }

}
