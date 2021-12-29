package com.qiaoyn.reflect;

/**
 * 获取Class的几种实例
 * @author yn.qiao
 * @version 1.0
 * @ClassName Test01
 * @create 2021-12-28 14:54
 **/
public class Test01 {

    public static void main(String[] args) throws ClassNotFoundException {
        Person student = new Student();

        //通过对象获得
        Class c1 = student.getClass();

        //通过forname获取
        Class c2 = Class.forName("com.qiaoyn.reflect.Student");

        //通过.class获取
        Class c3 = Student.class;

        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());

        //获取父类实例
        Class c4 = c1.getSuperclass();
        System.out.println(c4);

    }
}
