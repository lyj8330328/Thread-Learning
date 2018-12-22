package com.juc.copyonwritearraylist;

import java.lang.reflect.Field;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: 98050
 * @Time: 2018-12-21 20:48
 * @Feature: CopyOnWriteArrayList的使用
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {

        final CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>();

        /**
         * 线程1将0——10填充到list中
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.add(i);
                }
            }
        }).start();


        /**
         * 线程2将10——20填充到list中
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 10; i < 20; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.add(i);
                }
            }
        }).start();

        Thread.sleep(1000);


        Class<CopyOnWriteArrayList> calss = (Class<CopyOnWriteArrayList>) list.getClass();
        Field field = calss.getDeclaredField("array");
        field.setAccessible(true);
        Object[] objects = (Object[]) field.get(list);
        System.out.println("list的容量：" + objects.length);
        for (int i = 0; i < objects.length; i++) {
            System.out.println("第"+(i+1)+"个元素：" + objects[i]);
        }
    }

}
