package com.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 98050
 * @Time: 2018-12-06 21:30
 * @Feature: 使用阻塞队列实现生产者消费者模式
 */
public class Test003 {

    public static void main(String[] args) throws InterruptedException {

        //1.定义阻塞队列
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<String>(3);
        ProductThread productThread = new ProductThread(blockingQueue);
        ConsumerThread consumerThread = new ConsumerThread(blockingQueue);
        Thread thread1 = new Thread(productThread);
        Thread thread2 = new Thread(consumerThread);

        thread1.start();
        thread2.start();

        //5秒后停止生产者线程
        Thread.sleep(5*1000);
        productThread.stop();
    }
}

/**
 * 生产者线程
 */
class ProductThread implements Runnable{

    private BlockingQueue<String> blockingQueue;

    /**
     * 保持可见性和禁止重排序
     */
    private volatile Boolean flag = true;
    /**
     * 保证线程安全
     */
    AtomicInteger atomicInteger = new AtomicInteger();

    public ProductThread(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+",生产者线程已经启动.....");
        while (flag) {
            String data = atomicInteger.incrementAndGet() + "";
            try {
                boolean result;
                result = blockingQueue.offer(data, 2, TimeUnit.SECONDS);

                if (result) {
                    System.out.println(Thread.currentThread().getName() + ",将" + data + "放入队列成功.......");
                } else {
                    System.out.println(Thread.currentThread().getName() + ",将" + data + "放入队列失败.......");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "，生产者停止生产..........");
    }

    public void stop(){
        this.flag = false;
    }
}

/**
 * 消费者线程
 */
class ConsumerThread implements Runnable{

    private Boolean flag = true;
    private BlockingQueue<String> blockingQueue;

    public ConsumerThread(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"消费者线程开始启动.........");
        while (flag){
            try {
                String data = blockingQueue.poll(2,TimeUnit.SECONDS);
                if (data == null || "".equals(data)){
                    flag = false;
                    System.out.println(Thread.currentThread().getName() + ",消费者等待时间超过2秒，停止");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + ",消费者从队列中获取到消息：" + data);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}