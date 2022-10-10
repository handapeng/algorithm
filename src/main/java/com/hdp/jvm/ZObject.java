package com.hdp.jvm;

/**
 * @author HDP
 * @ClassName: ZObject
 * @Description:读屏障
 * @date 2022/10/10 22:44
 */
public class ZObject {
    ZObject instance = null;
    int i = 13;

    public void ReadBarrier() {
        ZObject A = new ZObject();
        A.instance = new ZObject();
        System.gc();
        //..读屏障代码
        ZObject D = A.instance; //堆中读引用，需要加读屏障
        ZObject E = D; // 不需要读屏障，D只是一个指针，不是堆中读引用
        D.hashCode(); // 不需要读屏障，
        int j = D.i; // 不需要读屏障，D.i虽然涉及到了成员变量但是是基本数据类型
    }
}
