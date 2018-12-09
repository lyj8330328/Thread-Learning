package com.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 98050
 * @Time: 2018-12-07 16:01
 * @Feature: 自定义线程池
 */
public class Test006 {

    public static void main(String[] args) {
        /**
         * 核心线程数1
         * 最大线程数2
         * 线程空闲超时时间
         * 缓存队列大小3
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(3));
        threadPoolExecutor.execute(new TaskThread("任务1"));
        threadPoolExecutor.execute(new TaskThread("任务2"));
        threadPoolExecutor.execute(new TaskThread("任务3"));
        threadPoolExecutor.execute(new TaskThread("任务4"));
        threadPoolExecutor.execute(new TaskThread("任务5"));
        threadPoolExecutor.execute(new TaskThread("任务6"));

        threadPoolExecutor.shutdown();
    }
}

class TaskThread implements Runnable{

    private String threadName;

    public TaskThread(String threadName) {
        this.threadName = threadName;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+threadName);
    }
}
