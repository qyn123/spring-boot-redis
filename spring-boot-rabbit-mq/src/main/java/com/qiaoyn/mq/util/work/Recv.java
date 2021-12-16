package com.qiaoyn.mq.util.work;

import com.qiaoyn.mq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName Recv
 * @create 2021-12-13 17:28
 **/
public class Recv {

    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        //创建会话通道,生产者和mq服务所有通信都在channel通道中完成
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicQos(1);
        //实现消费方法
        DefaultConsumer consumer = new DefaultConsumer(channel){
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body,"utf-8");
                System.out.println(" [消费者1] received : " + msg + "!");
                //模拟任务耗时1s
                try { TimeUnit.SECONDS.sleep(1); } catch (Exception e) { e.printStackTrace(); }
            }
        };
        // 监听队列，第二个参数：是否自动进行消息确认。
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
