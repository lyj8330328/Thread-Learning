package com.util.hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: 98050
 * @Time: 2018-12-23 17:14
 * @Feature: HashMap线程安全问题
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        final Map<String,String> map = new HashMap<String, String>();


        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程1放入0——9
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    map.put(Thread.currentThread().getName() + "放入" + i, i + "");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程2放入10——19
                for (int i = 10; i < 20; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    map.put(Thread.currentThread().getName() + "放入" + i, i + "");
                }
            }
        }).start();


        Thread.sleep(1000);
        System.out.println("map的大小：" + map.size());
        Iterator iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            // 获取key和value
            System.out.println("key：" + entry.getKey() + ", value：" + entry.getValue());
        }
    }


}
