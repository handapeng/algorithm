package com.hdp.juc.threadbase;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author HDP
 * @ClassName: ThreadExecuteTest
 * @Description:
 * @date 2022/10/31 14:40
 */
@Slf4j
public class ThreadExecuteTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.debug("通过Runnable方式执行任务");
            }
        };
        //操作系统创建线程
        //java Thread-->jvm JavaThread--> os Thread
        new Thread(runnable).start();
        FutureTask task = new FutureTask<>(new Callable() {
            @Override
            public Object call() throws Exception {
                log.debug("通过Callable执行任务");
                Thread.sleep(3000);
                return "返回任务结果";
            }
        });
        new Thread(task).start();
        log.debug("结果为：{}",task.get());
    }
}
