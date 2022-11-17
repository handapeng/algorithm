package com.hdp.juc.threadactiveness;

import lombok.extern.slf4j.Slf4j;

/**
 * @author HDP
 * @ClassName: LiveLockTest
 * @Description:
 * 活锁
 * @date 2022/11/17 10:09
 */
@Slf4j
public class LiveLockTest {
    /**
     * 定义一个勺子，ower 表示这个勺子的拥有者
     */
    static class Spoon {
        Diner woner;

        public Spoon(Diner woner) {
            this.woner = woner;
        }

        public String getWonerName() {
            return woner.getName();
        }

        public void setWoner(Diner woner) {
            this.woner = woner;
        }
        //表示正在用餐
        public void use() {
            log.info("{}用这个勺子吃饭。",woner.getName());
        }
    }

    /**
     * 定义一个晚餐类
     */
    static class Diner {
        private boolean isHungry;
        //用餐者的名字
        private String name;

        public Diner(boolean isHungry, String name) {
            this.isHungry = isHungry;
            this.name = name;
        }
        //和某人吃饭
        public void eatWith(Diner diner, Spoon sharedSpoon) {
            try {
                synchronized (sharedSpoon) {
                    while (isHungry) {
                        //当前用餐者和勺子拥有者不是同一个人，则进行等待
                        while (!sharedSpoon.getWonerName().equals(name)) {
                            sharedSpoon.wait();
                        }
                        if (diner.isHungry()) {
                            log.info("{}：亲爱的我饿了，然后{}把勺子给了{}",
                                    diner.getName(), name, diner.getName());
                            sharedSpoon.setWoner(diner);
                            //唤醒等待的线程
                            sharedSpoon.notifyAll();
                        } else {
                            //用餐
                            sharedSpoon.use();
                            sharedSpoon.setWoner(diner);
                            isHungry = false;
                        }
                        Thread.sleep(500);
                    }
                }
            } catch (
                    InterruptedException e) {
                log.info("{} is Interrupted.",name);
            }
        }
        public boolean isHungry() {
            return isHungry;
        }

        public String getName() {
            return name;
        }
    }
    public static void main(String[] args) {
        final Diner husband = new Diner(true, "丈夫");
        final Diner wife = new Diner(true, "妻子");
        //最开始牛郎持有勺子
        final Spoon sharedSpoon = new Spoon(husband);

        Thread h = new Thread(() -> {
            wife.eatWith(husband, sharedSpoon);
        });
        h.start();

        //牛郎和织女吃饭
        Thread w = new Thread(() -> {
            husband.eatWith(wife, sharedSpoon);
        });
        w.start();
        try {
            Thread.sleep(1000);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
//        h.interrupt();
//        w.interrupt();
    }
}
