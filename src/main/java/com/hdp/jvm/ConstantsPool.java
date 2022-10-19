package com.hdp.jvm;

/**
 * @author HDP
 * @ClassName: ConstantsPool
 * @Description:
 * @date 2022/10/19 10:20
 */
public class ConstantsPool {
    public static void main(String[] args) {
        //这种方式创建的对象只会在常量池里，创建对象时会用equals(key) 判断常量池中是否有这个字面量，没有则创建在返回引用，有则直接返回引用
        String s = "test";  //s指向常量池中的引用
        //对象分配到堆里，在常量池里也会创建这个字面量，创建对象会检查常量池里是否有这个字符串，没有则在常量池创建这个字符串 再在堆里创建这个对象，有则在堆里创建这个对象，最后返回堆里的引用
        String s1 = new String("test"); // s1指向堆里的对象引用

        String s2 = s1.intern();  //检查常量池里是否有这个字符串，没有则返回堆里的对象引用 将返回的引用指向s1 ，有则返回常量池里的引用

        System.out.println(s1 == s2);
        String s11 =new String("he") + new String("llo");
        String s21 = s11.intern();

        System.out.println(s11 == s21);//jdk7及以上创建了5个对象


    }
}
