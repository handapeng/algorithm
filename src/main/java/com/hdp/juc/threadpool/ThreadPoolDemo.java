package com.hdp.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author HDP
 * @ClassName: ThreadPoolDemo
 * @Description:
 * @date 2022/11/15 9:38
 */
public class ThreadPoolDemo {
    //1、corePoolSize：核心线程大小，当提交一个任务到线程池时，线程池会创建一个线程来执行任务，即使有其他空闲线程可以处理任务也会创新线程，
    // 等到工作的线程数大于核心线程数时就不会再创建了。如果调用了线程池的prestartAllCoreThreads方法，线程池会提前把核心线程都创造好，并启动
    //2、maximumPoolSize：线程池允许创建的最大线程数。如果队列满了，且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。
    // 如果我们使用了无界队列，那么所有的任务会加入队列，这个参数就没有什么效果了
    //3、keepAliveTime：线程池的工作线程空闲后，保持存活的时间。如果没有任务处理了，有些线程会空闲，空闲的时间超过了这个值，会被回收掉。
    // 如果任务很多，并且每个任务的执行时间比较短，避免线程重复创建和回收，可以调大这个时间，提高线程的利用率
    //4、unit：keepAliveTIme 的时间单位，可以选择的单位有天、小时、分钟、毫秒、微秒、千分之一毫秒和纳秒。类型是一个枚举java.util.concurrent.TimeUnit
    //5、workQueue：工作队列，用于缓存待处理任务的阻塞队列，常见的有 4 种，
    //		1）ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按照先进先出原则对元素进行排序
    //		2）LinkedBlockingQueue：是一个基于链表结构的阻塞队列，此队列按照先进先出排序元素，吞吐量通常要高于 ArrayBlockingQueue。
    //		静态工厂方法Executors.newFixedThreadPool使用了这个队列。
    //3）SynchronousQueue：一个不存储元素的阻塞队列，每个插入操作必须等到另外一个线程调用移除操作，否则插入操作一直处理阻塞状态，吞吐量通常要高于LinkedBlockingQueue，
    // 静态工厂方法Executors.newCachedThreadPool使用这个队列
    //		4）PriorityBlockingQueue：优先级队列，进入队列的元素按照优先级会进行排序
    //6、threadFactory：线程池中创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字
    //7、handler：饱和策略(拒绝策略)，当线程池无法处理新来的任务了，那么需要提供一种策略处理提交的新任务，默认有 4 种策略
    //		1）AbortPolicy：直接抛出异常
    //		2）CallerRunsPolicy：在当前调用者的线程中运行任务，即随丢来的任务，由他自己去处理
    //		3）DiscardOldestPolicy：丢弃队列中最老的一个任务，即丢弃队列头部的一个任务，然后执行当前传入的任务
    //		4）DiscardPolicy：不处理，直接丢弃掉，方法内部为空
    //		自定义拒绝策略需要实现RejectedExecutionHandler接口。任务无法处理的时候，更推荐自定义策略而不是使用默认策略，如记录一下日志等；
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(Thread.currentThread().getName()+":reject");
            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),rejectedExecutionHandler);
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(new MyTask(i));
        }
    }
    static class MyTask implements Runnable {
        int i = 0;

        public MyTask(int i) {
            this.i = i;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "程序员做第" + i + "个项目");
            try {
                Thread.sleep(3000);
            } catch (
                    InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
