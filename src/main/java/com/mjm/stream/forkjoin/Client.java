package com.mjm.stream.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author majun
 * @date 2018/10/30 23:19
 *
 * 当数据量大的时候 特别明显
 */
public class Client {

    public static void main(String[] args) {

        Instant start = Instant.now();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinCalculate(0,100000);
        Long result = forkJoinPool.invoke(forkJoinTask);
        System.out.println(result);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());

        /**
         * java8 并行流
         *并行流 与 串行流的切换
         *
         * .parallel    cpu 利用率升高
         * .sequential
         */
        long paraRes = LongStream.rangeClosed(0, 1000000000)
                .parallel()
                .sequential()
                .reduce(0, Long::sum);


    }

    public long testFor(int num){
        Instant start = Instant.now();
        long sum = 0;
        for (int i = 0; i <= num; i++) {
            sum += i;
        }
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
        return sum;
    }
}
