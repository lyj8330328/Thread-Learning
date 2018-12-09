package com.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 98050
 * @Time: 2018-12-06 21:10
 * @Feature: 阻塞队列ArrayBlockingQueue
 */
public class Test002 {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(3);
        arrayBlockingQueue.offer("张三");
        arrayBlockingQueue.offer("李四",3, TimeUnit.SECONDS);
        arrayBlockingQueue.offer("王五",3, TimeUnit.SECONDS);
        boolean result = arrayBlockingQueue.offer("赵六",3, TimeUnit.SECONDS);
        System.out.println(result);
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());


    }
}
