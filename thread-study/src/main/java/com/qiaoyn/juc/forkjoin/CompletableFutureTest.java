package com.qiaoyn.juc.forkjoin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName CompletableFutureTest
 * @create 2021-12-22 16:58
 **/
public class CompletableFutureTest {

    public static  void main(String[] args) throws ExecutionException, InterruptedException {


        //异步执行-成功回调-失败回调
        //异步调用 发起一个请求:无返回值runAsync
        /*CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "runAsync ");
        });*/

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync=>Integer");
            int i = 1 / 0;  //制造错误
            return 200;
        });
        //成功时的回调:whenComplete
        //失败时的回调:exceptionally
        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t：" + t);   //输出看看t是什么
            System.out.println("u：" + u);  //输出看看u是什么
        }).exceptionally((e) -> {
            e.getMessage();  //打印异常信息
            return 400;       //失败返回
        }).get());
    }
}
