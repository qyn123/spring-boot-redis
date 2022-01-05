package com.qiaoyn.hashmap;

/**
 * 如何解决hash冲突问题
 * @author yn.qiao
 * @version 1.0
 * @ClassName ChainMap
 * @create 2022-01-03 20:07
 **/
public class ChainMap<K,V> {

    private Entry<K,V> [] entryList = new Entry[10000];



    public void put(K k,V v){
        //根据key计算存放位置
        int index = k.hashCode() % entryList.length;
        Entry<K, V> entry = entryList[index];
        //如果entry为null,说明未发生冲突
        if(entry == null){
            entryList[index] = new Entry<>(k, v);
        } else {
            //说明此时该位置已经被存放了，则发生了hash冲突
            entry.next = new Entry<>(k, v);
        }
    }


    public V get(K k){
        //根据key计算存放位置
        int index = k.hashCode() % entryList.length;
        for (Entry<K,V> entry = entryList[index]; entry != null; entry = entry.next){
            if(entry.k.equals(k) || entry.k == k){
                return entry.v;
            }
        }
        return null;
    }



    public static void main(String[] args) {
        ChainMap<Object, Object> chainMap = new ChainMap<>();
        chainMap.put(97,"a97");
        chainMap.put("b",34);
        chainMap.put("a",34);
        System.out.println(chainMap.get(97));
        System.out.println(chainMap.get("a"));
    }
}
