package com.hdp.juc.blokingqueue.delayqueue;

import java.util.Objects;
import java.util.concurrent.DelayQueue;

/**
 * @author HDP
 * @ClassName: OrderOverTimeClose
 * @Description: 使用延时队列DelayQueue实现订单超时关闭
 * 后台守护线程不断的执行检测工作
 * @date 2022/12/8 19:07
 */
public class OrderOverTimeClose {
    private volatile static OrderOverTimeClose orderOverTimeClose = null;

    private OrderOverTimeClose() {
    }

    public static OrderOverTimeClose getInstance() {
        if (orderOverTimeClose == null) {
            synchronized (OrderOverTimeClose.class) {
                if (orderOverTimeClose == null) {
                    orderOverTimeClose = new OrderOverTimeClose();
                }
            }
        }
        return orderOverTimeClose;
    }

    /**
     * 守护线程
     */
    private Thread mainThread;

    /**
     * 启动方法
     */
    public void init() {
        mainThread = new Thread(() -> execute());
        mainThread.setDaemon(true);
        mainThread.setName("守护线程->");
        mainThread.start();
    }

    /**
     * 创建空延时队列
     */
    private DelayQueue<OrderInfo> delayQueue = new DelayQueue<>();

    /**
     * 读取延时队列，关闭超时订单
     */
    private void execute() {
        while (true) {
            try {
                //从队列里获取超时的订单
                OrderInfo orderInfo = delayQueue.take();
                // 检查订单状态，是否已经成功，成功则将订单从队列中删除。
                if (Objects.equals(orderInfo.getStatus(), "成功")) {
                    //TODO 支付成功的订单处理逻辑
                    System.out.println("线程：" + Thread.currentThread().getName() + "，订单号："
                            + orderInfo.getOrderNo() + "，订单状态："
                            + orderInfo.getStatus() + "，订单创建时间："
                            + orderInfo.getCreateTime()
                            + "，订单超时时间：" + orderInfo.getExpTime() + "，当前时间："
                            + OrderPay.getTime(System.currentTimeMillis()));
                    Thread.sleep(2000);
                } else {
                    // TODO 超时未支付订单处理逻辑
                    System.out.println("线程：" + Thread.currentThread().getName() + "，订单号："
                            + orderInfo.getOrderNo() + "，变更订单状态为：超时关闭"
                            + "，订单创建时间："
                            + orderInfo.getCreateTime()
                            + "，订单超时时间：" + orderInfo.getExpTime() + "，当前时间："
                            + OrderPay.getTime(System.currentTimeMillis()));
                    Thread.sleep(2000);
                }
            } catch (
                    InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 插入订单到延时队列中
     */
    public void OrderPutQueue(OrderInfo orderInfo, String createTime, String overTime) {
        System.out.println("订单号：" + orderInfo.getOrderNo()
                + "，订单状态：" + orderInfo.getStatus()
                + "，订单创建时间：" + createTime
                + "，订单过期时间：" + overTime);
        delayQueue.add(orderInfo);
    }
}
