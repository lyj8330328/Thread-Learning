package com.util.hashtable;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: 98050
 * @Time: 2018-12-27 15:42
 * @Feature:
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        final Hashtable<String,String> hashtable = new Hashtable();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    hashtable.put(Thread.currentThread().getName() + "放入" + i, i +"");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 10; i < 20; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    hashtable.put(Thread.currentThread().getName() + "放入" + i, i +"");
                }
            }
        }).start();

        Thread.sleep(1000);
        System.out.println("hashtable的大小：" + hashtable.size());
        Iterator iterator = hashtable.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            // 获取key和value
            System.out.println("key：" + entry.getKey() + ", value：" + entry.getValue());
        }
    }
}
