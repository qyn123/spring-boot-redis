package com.qiaoyn.juc.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName ArrayBlockQueueTest
 * @create 2021-12-21 14:31
 **/
public class ArrayBlockQueueTest {

    public static void main(String[] args) {
        test04();

    }
    /**
     * ArrayBlockingQueue数组队列：底层是一个数组
     * FIFO(first input first output)先进先出原则，需要指定大小
     */
    /**
     * add与remove添加、取出如果元素超过队列长度或者队列为空会抛出异常
     */
    public static void test01(){
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        //添加
        queue.add("a");
        queue.add("b");
        queue.add("c");
        //add方法添加队列大于指定长度，add()方法添加成功会返回true,失败会抛出异常 "Queue full"
        //queue.add("c");
        //取:remove()方法为取出元素，当元素都取出之后，再取出元素就会抛出异常。 java.util.NoSuchElementException

        //判断对首元素
        System.out.println(queue.element());

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
       // System.out.println(queue.remove());

    }

    /**
     * offer和poll添加、取出，当添加任务超过队列长度，会返回false,取出任务当队列为空时会返回null
     */
    public static void test02(){
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        //添加元素
        queue.offer("a");
        queue.offer("b");
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("d"));
        //判断对首元素
        System.out.println(queue.peek());
        //取元素
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    /**
     * 阻塞等待：(put/take)当添加任务超过队列长度，会处于阻塞状态
     */
    public static void test03(){
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        try {
            queue.put("a");
            queue.put("b");
            queue.put("c");
           // queue.put("d");
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 阻塞等待，可以设置等待时间
     */
    public static void test04(){
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        try {
            queue.offer("a");
            queue.offer("b");
            queue.offer("c");
            //阻塞等待,可以设置等待时间
            queue.offer("d", 2, TimeUnit.SECONDS);
            System.out.println(queue.poll());
            System.out.println(queue.poll());
            System.out.println(queue.poll());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
