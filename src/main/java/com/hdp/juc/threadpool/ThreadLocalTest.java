package com.hdp.juc.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * @author HDP
 * @ClassName: ThreadLocalTest
 * @Description:
 * @date 2022/11/21 14:08
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        Person person = new Person();
        new Thread(() -> {
            person.setName("test");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (
                    InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程1===" + person.getName());
        }).start();
        new Thread(() -> {
            person.setName("test2");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (
                    InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程2===" + person.getName());
            //手动清除Entry对象
            person.remove();
            System.out.println("线程2===" + person.getName());
        }).start();

    }
    public static class Person {
        private ThreadLocal<String> name = new ThreadLocal<>();

        public String getName() {
            return this.name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }
        //手动清除Entry对象
        public void remove() {
            this.name.remove();
        }
    }
}
