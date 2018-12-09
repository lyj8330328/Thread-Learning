package com.concurrent.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author: 98050
 * @Time: 2018-12-06 20:46
 * @Feature: 非阻塞队列的使用
 */
public class Test001 {

    public static void main(String[] args) {
        //非阻塞队列，无界
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<String>();
        concurrentLinkedQueue.add("张三");
        concurrentLinkedQueue.add("李四");
        concurrentLinkedQueue.add("王五");
        while (concurrentLinkedQueue.size() > 0){
            //poll()会删除队列中的元素，peek()不删除
            System.out.println(concurrentLinkedQueue.peek());
        }
        System.out.println("剩余个数："+concurrentLinkedQueue.size());
    }
}
