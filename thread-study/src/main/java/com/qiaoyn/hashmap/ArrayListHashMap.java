package com.qiaoyn.hashmap;


import java.util.ArrayList;
import java.util.List;

/**
 * 基于List的hashMap实现方式
 * @author yn.qiao
 * @version 1.0
 * @ClassName ArrayListHashMap
 * @create 2022-01-03 12:44
 **/
public class ArrayListHashMap<K, V> {
    public static void main(String[] args) {
        ArrayListHashMap<Object,Object> map = new ArrayListHashMap<>();
        map.put("a",12);
        map.put(97,"b");
        System.out.println(map.get("a"));

    }


    public List<Entry> list = new ArrayList<>();

    public void put(K k, V v) {
        list.add(new Entry(k, v));
    }

    public V get(K k) {
        return list.stream().filter(entry -> k.equals(entry.k)).findFirst().map(entry -> (V) entry.v).orElse(null);
    }


}


