package com.qiaoyn.hashmap;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName Entry
 * @create 2022-01-03 19:17
 **/
public class Entry<K,V> {

    K k;
    V v;
    Entry<K,V> next;

    public Entry(K k, V v) {
        this.k = k;
        this.v = v;
    }

}
