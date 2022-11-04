package com.hdp.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @author HDP
 * @ClassName: LongAccumulatorTest
 * @Description:
 * @date 2022/11/4 14:02
 */
public class LongAccumulatorTest {
    public static void main(String[] args) throws InterruptedException {
        // 累加 x+y
        LongAccumulator accumulator = new LongAccumulator(Long::sum, 0);
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        // 1到9累加
        IntStream.range(1, 10).forEach(value -> executorService.submit(() -> accumulator.accumulate(value)));
        Thread.sleep(2000);
        System.out.println(accumulator.getThenReset());
    }
}
