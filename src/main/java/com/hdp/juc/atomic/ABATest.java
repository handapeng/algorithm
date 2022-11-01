package com.hdp.juc.atomic;

/**
 * @author HDP
 * @ClassName: ABATest
 * @Description:
 * @date 2022/11/1 11:48
 */
public class ABATest {

    //JAVA内存模型,用来屏蔽各种硬件和操作系统的内存访问差异，以实现让Java程序在各种平台下都能达到一致的内存访问效果，
    // 主要目的是定义程序中各种变量的访问规则，即关注在虚拟机中把变量值存储到内存和从内存中取出变量值这样的底层细节。
    //主内存、工作内存、线程关系
    //关系如下所示
    //主内存与工作内存之间具体的交互协议，即一个变量如何从主内存拷贝到工作内存、如何从工作内存同步回主内存这一类的实现细节，
    // Java内存模型中定义了load、store、read、write、lock、unlock、use、assign这8种操作来完成。Java虚拟机实现时必须保证下面提及的每一种操作都是原子的、不可再分的
    //
}
