package com.hdp.juc.factory;

/**
 * @author HDP
 * @ClassName: VisibilityTest
 * @Description:
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp  查看汇编指令
 * hsdis-amd64.dll
 * @date 2022/10/25 10:33
 */
public class VisibilityTest {
    //加volatile可以跳出循环 内存屏障
    private  boolean flag = true;
    //改为Integer 可以跳出循环 final字段保证可见性 内存屏障
    // lock addl $0x0,(%rsp)     ;*putstatic finalRefCount
    private Integer count = 0;

    public void refresh() {
        flag = false;
        System.out.println(Thread.currentThread().getName() + "修改flag：" + flag);
    }

    //storeLoad  JVM内存屏障 ---》 （汇编层面指令） lock addl $0x0,(%rsp)
    // lock前缀指令不是内存屏障的指令，但是有内存屏障的效果，修改的值立即载入主内存，让其余共享变量副本的缓存失效
    public void load() {
        System.out.println(Thread.currentThread().getName() + "开始执行。。。");
        while (flag) {
            count++;
            //内存屏障
//            UnsafeFactory.getUnsafe().storeFence();
            //释放时间片，上下文切换，共享变量的副本失效，加载上下文读取主内存的flag=false
//            Thread.yield();
            //synchronized  内存屏障
//            System.out.println(count);
            //内存屏障
//            LockSupport.unpark(Thread.currentThread());
            //上下文切换
//            shortWait(10000);
//            try {
//                //内存屏障
//                Thread.sleep(1);
//            } catch (
//                    InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            //Java中可见性如何保证？ 方式归类有两种：
            //1. jvm层面 storeLoad内存屏障  ===》x86  lock替代mfence
            //2. 上下文切换  Thread.yield();

        }
        System.out.println(Thread.currentThread().getName() + "跳出循环,count=" + count);
    }

    public static void main(String[] args) throws InterruptedException {
        VisibilityTest test = new VisibilityTest();
        Thread threadA = new Thread(test::load, "threadA");
        threadA.start();
        Thread.sleep(1000);
        Thread threadB = new Thread(test::refresh, "threadB");
        threadB.start();
    }

    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do
        {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}
