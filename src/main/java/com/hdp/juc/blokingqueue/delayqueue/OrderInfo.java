package com.hdp.juc.blokingqueue.delayqueue;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author HDP
 * @ClassName: OrderInfo
 * @Description:订单对象
 * @date 2022/12/8 18:56
 */
public class OrderInfo implements Delayed, Serializable {
    private static final long serialVersionUID = 1L;
    private String orderNo;// 订单号
    private String status;// 订单状态
    private String expTime;// 订单过期时间
    private String createTime;//订单创建时间

    /**
     * 用于延时队列内部比较排序：当前订单的过期时间 与 队列中对象的过期时间 比较
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nowThreadtime;
        long queueThreadtime;
        try {
             nowThreadtime = formatter.parse(this.expTime).getTime();
            queueThreadtime = formatter.parse(((OrderInfo)o).expTime).getTime();
        } catch (
                ParseException e) {
            throw new RuntimeException(e);
        }
        return Long.compare(nowThreadtime, queueThreadtime);
    }

    /**
     * 时间单位：秒
     * 延迟关闭时间 = 过期时间 - 当前时间
     * @param unit the time unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = 0;
        try {
            time = formatter.parse(this.expTime).getTime();
        } catch (
                ParseException e) {
            throw new RuntimeException(e);
        }
        return time - System.currentTimeMillis();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpTime() {
        return expTime;
    }

    public void setExpTime(String expTime) {
        this.expTime = expTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
