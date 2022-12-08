package com.hdp.juc.threadlocal;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author HDP
 * @ClassName: MapTest
 * @Description:
 * @date 2022/11/21 9:38
 */
public class MapTest {
    public static void main(String[] args) {
        HashMap map = new HashMap<>();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
        map.put("1", 2);
        concurrentHashMap.put("2", 2);
        concurrentHashMap.containsKey("");
        //1.7: ConcurrentHashMap类中包含两个静态内部类 HashEntry 和 Segment。HashEntry 用来封装映射表的键值对；
        //Segment 用来充当锁的角色，每个 Segment 对象守护整个散列映射表的若干个桶。每个桶是由若干个
        //HashEntry 对象链接起来的链表。一个 ConcurrentHashMap 实例中包含由若干个 Segment 对象组成的数组。
        //HashEntry 用来封装散列映射表中的键值对。在 HashEntry 类中，key，hash 和 next 域都被声明为 final 型，
        //value 域被声明为 volatile 型。lock(ReentrantLock)加在Segment上面。1.7size计算是先采用不加锁的方式，连续计算元素的个数，
        //最多计算3次：1、如果前后两次计算结果相同，则说明计算出来的元素个数是准确的；2、如果前后两次计算结果都不同，则给每个Segment进行加锁，再计算一次元素的个数
        //1.8中放弃了Segment臃肿的设计，取而代之的是采用Node + CAS + Synchronized来保证并发安全进行实现，1.8中使用一个volatile类型的变量baseCount记录元素的个数，
        //当插入新数据或则删除数据时，会通过addCount()方法更新baseCount，通过累加baseCount和CounterCell数组中的数量，即可得到元素的总个数
    }
}
