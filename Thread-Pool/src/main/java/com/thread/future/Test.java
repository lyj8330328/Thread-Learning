package com.thread.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Author: 98050
 * @Time: 2018-12-07 23:17
 * @Feature:
 */
public class Test {

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<String>(new RealData("参数"));
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(futureTask);
        System.out.println("请求完毕");
        try {
            //执行其它工作
            System.out.println("开始执行其它任务，耗时3秒");
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+i);
                Thread.sleep(1000);
            }
            System.out.println("请求结果："+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
