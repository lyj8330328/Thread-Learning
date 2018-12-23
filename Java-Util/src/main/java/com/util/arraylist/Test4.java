package com.util.arraylist;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 98050
 * @Time: 2018-12-21 20:48
 * @Feature: ArrayList非线程安全
 */
public class Test4 {

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {

         final List<String> list = new ArrayList<String>();

        /**
         * 线程1将0——10填充到list中
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.add(Thread.currentThread().getName() + "添加:" + i);
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
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.add(Thread.currentThread().getName() + "添加:" + i);
                }
            }
        }).start();

        Thread.sleep(1000);


        Class<List> calss = (Class<List>) list.getClass();
        Field field = calss.getDeclaredField("elementData");
        field.setAccessible(true);
        Object[] objects = (Object[]) field.get(list);
        System.out.println("list的容量：" + objects.length);
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
    }

}
