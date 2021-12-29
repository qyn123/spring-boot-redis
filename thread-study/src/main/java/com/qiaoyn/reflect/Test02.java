package com.qiaoyn.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName Test02
 * @create 2021-12-28 16:46
 **/
public class Test02 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class c1 = Class.forName("com.qiaoyn.reflect.Person");

        Person person = (Person) c1.newInstance();
        //包名+类名
        System.out.println(c1.getName());
        //类名
        System.out.println(c1.getSimpleName());
        //获取父类名称(父类包名+类名)
        System.out.println(c1.getSuperclass());
        //获取包名
        System.out.println(c1.getPackage());
        //获取实例
        System.out.println(c1.newInstance());

        //获得当前类实现的类或是接口
        Class[] interfaces = c1.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println("anInterface="+anInterface);

        }

        //类加载器(sun.misc.Launcher$AppClassLoader@18b4aac2)
        System.out.println(c1.getClassLoader());

        System.out.println("====================获取所有公有构造方法====================");
        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("constructor="+constructor);
        }

        System.out.println("=================获取所有构造方法=======================");
        Constructor[] declaredConstructors = c1.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println("declaredConstructor"+declaredConstructor);
        }

        //获取指定的构造方法
        System.out.println("=================获取指定的构造方法=======================");
        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class);
        System.out.println(declaredConstructor);

        System.out.println("================获得该类所有公有的方法=====================");
        Method[] methods = c1.getMethods();
        for (Method method : methods) {
            System.out.println("method="+method);
        }
        System.out.println("================获得该类所有方法======================");
        Method[] declaredMethods = c1.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("declaredMethod="+declaredMethod);
        }

        //获得该类某个方法
        System.out.println("==============获得该类某个方法===========================");
        System.out.println(c1.getDeclaredMethod("test", null));

        System.out.println("==============获得该类公有的某个方法===========================");
        System.out.println(c1.getMethod("setName", String.class));

        Method setName = c1.getMethod("setName", String.class);
      //  setName.setAccessible(true);
        setName.invoke(person,"tom");
        System.out.println(person.getName());

        //操作私有方法
        Method test = c1.getDeclaredMethod("test01", int.class);
        //关闭安全监测
        test.setAccessible(true);
        test.invoke(person,12);

    }
}
