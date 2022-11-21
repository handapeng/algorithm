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
    }
}
