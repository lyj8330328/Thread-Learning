package com.util.hashset;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: 98050
 * @Time: 2018-12-23 15:56
 * @Feature:
 */
public class Test2 {

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        final Set<String> set = new HashSet<String>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    set.add(Thread.currentThread().getName() + "添加：" + i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 10; i < 20; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    set.add(Thread.currentThread().getName() + "添加：" + i);
                }
            }
        }).start();

        Thread.sleep(1000);
        System.out.println("set的大小：" + set.size());
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
