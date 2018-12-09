package com.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 98050
 * @Time: 2018-12-07 13:50
 * @Feature:
 */
public class Test005 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        for (int i = 0; i < 10; i++) {
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
