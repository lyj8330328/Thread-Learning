package com.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 98050
 * @Time: 2018-12-07 11:33
 * @Feature: newScheduledThreadPool
 */
public class Test003 {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 20; i++) {
            final int temp = i;
            executorService.schedule(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+","+temp);
                }
            },2, TimeUnit.SECONDS);
        }
        executorService.shutdown();
    }
}
