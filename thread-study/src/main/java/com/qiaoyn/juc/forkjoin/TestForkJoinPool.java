package com.qiaoyn.juc.forkjoin;

import java.util.OptionalLong;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.Lock;
import java.util.stream.LongStream;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName TestForkJoinPool
 * @create 2021-12-22 15:14
 **/
public class TestForkJoinPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test01(); //3786 fork-join
        //test02(); // 5543 直接计算
        test03(); //535 并行流

    }

    public static void  test01() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> submit = pool.submit(new ForkJoinTaskTest(1L, 10_0000_0000L));
        Long sum = submit.get();
        long endTime = System.currentTimeMillis();
        System.out.println("sum=" + sum +",耗时"+ (endTime - startTime));
    }


    public static void  test03() {
        long startTime = System.currentTimeMillis();
        Long sum = LongStream.range(0L, 10_0000_0000L).parallel().reduce(Long::sum).getAsLong();
        long endTime = System.currentTimeMillis();
        System.out.println("sum=" + sum +",耗时"+ (endTime - startTime));
    }


    public static void  test02() {
        long startTime = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = 1L; i < 10_0000_0000L; i++) {
            sum += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("sum=" + sum +",耗时"+ (endTime - startTime));
    }
}

class ForkJoinTaskTest extends RecursiveTask<Long>{

    private static final Long MAX = 20000L;


    /**
     * 子任务的初始值
     */
    private Long start;

    /**
     * 子任务的结束值
     */
    private Long end;

    ForkJoinTaskTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // 如果条件成立，说明这个任务所需要计算的数值分为足够小了
        // 可以正式进行累加计算了
        if(end - start < MAX ){
            Long sum = 0L;
            for (Long i = this.start; i < this.end; i++){
                 sum += i;
            }
            return sum;
        } else {
            // 否则再进行任务拆分，拆分成两个任务
            ForkJoinTaskTest task1 = new ForkJoinTaskTest(start, (end + start) / 2);
            ForkJoinTaskTest task2 = new ForkJoinTaskTest((end + start) / 2 + 1, end);
            task1.fork();
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
