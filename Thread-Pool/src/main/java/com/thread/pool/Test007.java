package com.thread.pool;

import java.util.concurrent.*;

/**
 * @Author: 98050
 * @Time: 2018-12-07 21:05
 * @Feature: Callable
 */
public class Test007 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("主线程开始执行");
        final Future<String> submit = executorService.submit(new TaskCallable());
        String result = submit.get();
        System.out.println("执行结果："+result);
        executorService.shutdown();
    }
}

class TaskCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("正在执行任务，需要等待五秒，开始执行");
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+i);
            Thread.sleep(1000);
        }
        System.out.println("执行结束");
        return "success";
    }
}
