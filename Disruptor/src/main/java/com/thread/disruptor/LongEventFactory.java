package com.thread.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @Author: 98050
 * @Time: 2018-12-19 16:22
 * @Feature: 实例化LongEvent
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    public LongEvent newInstance() {
        return new LongEvent();
    }
}
