package com.qiaoyn.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试hashmap内存泄露问题:
 * -Xms3m -Xmx3m -XX:+HeapDumpOnOutOfMemoryError
 * @author yn.qiao
 * @version 1.0
 * @ClassName HashMapMemoryLeak
 * @create 2022-01-04 10:11
 **/
public class HashMapMemoryLeak {

    public static void main(String[] args) throws InterruptedException {
        Map<String,String> map = new HashMap<>(1000);
        int count = 0;
        while (true){
            map.put("tom","value");
            count++;
            if(count % 1000 == 0){
                System.out.println("map size: " + map.size());
                System.out.println("Free memory after count " + count + " is " + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "MB");
                Thread.sleep(10);
            }
        }

    }
}

class  Key{

    private String key;

    public Key(String key) {
        this.key = key;
    }

}
