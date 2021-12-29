package com.qiaoyn.reflect;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName Person
 * @create 2021-12-28 14:52
 **/
public class Person {

    String name;

    private int age;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    private void test(){
    }

    private void test01(int age){
        System.out.println(age);
    }


}

class Student extends  Person{

    public Student() {
        this.name = "学生";
    }


}

class Teacher extends  Person{

    public Teacher() {
        this.name = "老师";
    }
}
