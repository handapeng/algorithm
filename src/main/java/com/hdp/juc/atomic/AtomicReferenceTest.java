package com.hdp.juc.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author HDP
 * @ClassName: AtomicReferenceTest
 * @Description:
 * @date 2022/11/4 11:26
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        User user = new User("张三", 23);
        User user1 = new User("张三1", 25);
        User user2 = new User("张三2", 26);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(user);
        atomicReference.compareAndSet(user, user1);
        System.out.println(atomicReference.get());
        atomicReference.compareAndSet(user, user2);
        System.out.println(atomicReference.get());
    }
    @Data
    @AllArgsConstructor
    static
    class User {
        private String name;
        private Integer age;
    }
}
