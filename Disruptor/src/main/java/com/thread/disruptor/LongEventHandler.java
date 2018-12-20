package com.thread.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @Author: 98050
 * @Time: 2018-12-19 16:23
 * @Feature:  相当于消费者，获取生产者推送过来的消息
 */
public class LongEventHandler implements EventHandler<LongEvent> {


    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("消费者："+longEvent.getValue());
    }
}
