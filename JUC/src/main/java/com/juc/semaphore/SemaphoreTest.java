package com.juc.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private static final int THREAD_COUNT = 10;

    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    // 创建5个许可，允许5个并发执行
    private static Semaphore s = new Semaphore(5);

    public static void main(String[] args) {
        //创建10个线程执行任务
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        //同时只能有5个线程并发执行保存数据的任务
                        s.acquire();
                        System.out.println("线程" + Thread.currentThread().getName() + " 保存数据");
                        Thread.sleep(2000);
                        //5个线程保存完数据，释放1个许可，其他的线程才能获取许可，继续执行保存数据的任务
                        s.release();
                        System.out.println("线程" + Thread.currentThread().getName() + " 释放许可");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();

    }

}