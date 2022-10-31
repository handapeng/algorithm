package com.hdp.juc.threadbase;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author HDP
 * @ClassName: ThreadStateTest
 * @Description:
 * @date 2022/10/31 14:00
 */
@Slf4j
public class ThreadStateTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(LockSupport::park);
        log.debug("线程状态：{}", thread.getState());
        thread.start();
        log.debug("线程状态：{}", thread.getState());
        Thread.sleep(100);
        log.debug("线程状态：{}", thread.getState());
        //Java线程的生命周期有六种状态：
        //1、初始(NEW)：线程被构建，还没有调用 start()。
        //2、运行(RUNNABLE)：包括操作系统的就绪和运行两种状态。
        //3、阻塞(BLOCKED)：一般是被动的，在抢占资源中得不到资源，被动的挂起在内存，等待资源释放将其唤醒。线程被阻塞会释放CPU，不释放内存。
        //4、等待(WAITING)：进入该状态的线程需要等待其他线程做出一些特定动作（通知或中断）。
        //5、超时等待(TIMED_WAITING)：该状态不同于WAITING，它可以在指定的时间后自行返回。
        //6、终止(TERMINATED)：表示该线程已经执行完毕。

    }
}
