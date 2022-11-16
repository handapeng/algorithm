package com.hdp.juc.lock;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 栅栏与闭锁的关键区别在于，所有的线程必须同时到达栅栏位置，才能继续执行。
 */
public class CyclicBarrierTest2 {
    //保存每个学生的平均成绩
    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap();
    private ExecutorService threadPool = Executors.newFixedThreadPool(3);

    private CyclicBarrier cb = new CyclicBarrier(3, () -> {
        int result = 0;
        Set<String> set = map.keySet();
        for (String s : set) {
            result += map.get(s);
        }
        System.out.println("三人平均成绩为:" + (result / 3) + "分");
    });

    public void count() {
        for (int i = 0; i < 3; i++) {
            threadPool.execute(() -> {
                //获取学生平均成绩
                int score = (int) (Math.random() * 40 + 60);
                map.put(Thread.currentThread().getName(), score);
                System.out.println(Thread.currentThread().getName()
                        +"同学的平均成绩为："+score);
                try {
                    //执行完运行await(),等待所有学生平均成绩都计算完毕
                    cb.await();
                } catch (
                        InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (
                        BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
    public static void main(String[] args) {
        CyclicBarrierTest2 cyclicBarrierTest2 = new CyclicBarrierTest2();
        cyclicBarrierTest2.count();
    }


}
