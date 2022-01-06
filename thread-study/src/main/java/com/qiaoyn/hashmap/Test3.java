package com.qiaoyn.hashmap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName Test3
 * @create 2022-01-04 11:32
 **/
public class Test3 {

    public static void main(String[] args) {
        Map<Object, Object> map = new HashMap<>(16);
        map.put("a",12);
        map.put("a",13);
        System.out.println(3 & 14);
        System.out.println(map.get("a"));
        new Hashtable<>();
    }
}
