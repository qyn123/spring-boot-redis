package com.qiaoyn.hashmap;

/**
 * hashcode与equals之间的区别
 * @author yn.qiao
 * @version 1.0
 * @ClassName Test01
 * @create 2022-01-03 11:37
 **/
public class Test01 {
    public static void main(String[] args) {
        //如果两个对象的hashcode相等，那么内容是否相等？ 不一定
        //如果两个对象的equals比较内容值相等，hashcode值是否相等？ 相等
        String a = "a";
        Integer b = 97;
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());


        System.out.println("=======================================================");
        String c = "a";

        if(c.equals(a)){
            System.out.println(a.hashCode());
            System.out.println(c.hashCode());
        }
    }
}
