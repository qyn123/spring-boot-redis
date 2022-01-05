package com.qiaoyn.hashmap;

/**
 * 基于hash算法实现hashMap
 * 如果没有hash冲突的话，查询效率为O(1)
 * @author yn.qiao
 * @version 1.0
 * @ClassName HashMaps
 * @create 2022-01-03 19:19
 **/
public class HashMaps<K, V> {

    private Entry<K, V>[] entry = new Entry[10000];

    public void put(K k, V v) {
        int index = k.hashCode() % entry.length;
        entry[index] = new Entry<>(k, v);
    }


    public V get(K k) {
        int index = k.hashCode() % entry.length;
        Entry<K, V> entry = this.entry[index];
        if (entry == null) {
            return null;
        }
        return entry.v;
    }

    public static void main(String[] args) {
        //此时出现了hash冲突
        HashMaps<Object, Object> maps = new HashMaps<>();
        Integer b = 97;
        maps.put("a",123);
        maps.put("d",456);
        maps.put(b,234);
        System.out.println("a".hashCode() % 10000);
        System.out.println("c".hashCode() % 10000);
        System.out.println(b.hashCode() % 10000);
        System.out.println(maps.get("a"));
    }
}
