package com.thread.pool;

        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;

/**
 * @Author: 98050
 * @Time: 2018-12-07 11:06
 * @Feature: newCachedThreadPool
 */
public class Test001 {

    public static void main(String[] args) {
        //可缓存的线程池，重复利用，无限大小
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int temp = i;
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+","+temp);
                }
            });
        }
        executorService.shutdown();
    }
}
