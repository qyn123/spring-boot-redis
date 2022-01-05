package com.qiaoyn.juc.singletons;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 枚举单例模式
 * @author yn.qiao
 * @version 1.0
 * @ClassName SingletonEnum
 * @create 2021-12-27 14:41
 **/
public enum SingletonEnum {

    INSTANCE;

    public static SingletonEnum getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        SingletonEnum s = SingletonEnum.getInstance();
        SingletonEnum sUsual = SingletonEnum.getInstance();
        Constructor<SingletonEnum> constructor = SingletonEnum.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonEnum sReflection = constructor.newInstance();
        System.out.println(s.hashCode()+"\n"+sUsual.hashCode()+"\n"+sReflection.hashCode());
        System.out.println("正常情况下，实例化两个实例是否相同："+(s.hashCode() == sUsual.hashCode()));
        System.out.println("通过反射攻击单例模式情况下，实例化两个实例是否相同："+(s.hashCode() == sReflection.hashCode()));
    }

//    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
//        SingletonEnum singleton1 = SingletonEnum.INSTANCE;
//        SingletonEnum singleton2 = SingletonEnum.INSTANCE;
//        System.out.println("正常情况下，实例化两个实例是否相同："+(singleton1.hashCode() == singleton2.hashCode()));
//        //其父类的构造器
//        Constructor<SingletonEnum> constructor =  SingletonEnum.class.getDeclaredConstructor(String.class,int.class);
//       //反射无法操作私有属性，需要关闭安全检查
//        constructor.setAccessible(true);
//        SingletonEnum singleton3 = constructor.newInstance();
//        System.out.println(singleton1.hashCode()+"\n"+singleton2.hashCode()+"\n"+singleton3.hashCode());
//        System.out.println("通过反射攻击单例模式情况下，实例化两个实例是否相同："+(singleton1.hashCode() == singleton3.hashCode()));
//    }
}
