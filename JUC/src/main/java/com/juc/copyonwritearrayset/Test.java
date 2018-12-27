package com.juc.copyonwritearrayset;

import java.lang.reflect.Field;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: 98050
 * @Time: 2018-12-22 16:50
 * @Feature: copyOnWriteArraySet的使用
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {

        final CopyOnWriteArraySet<Integer> list = new CopyOnWriteArraySet<Integer>();

        /**
         * 线程1将0——9填充到set中
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
                    list.add(i);
                }
            }
        }).start();


        /**
         * 线程2将10——19填充到set中
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
                    list.add(i);
                }
            }
        }).start();

        Thread.sleep(1000);


        Class<CopyOnWriteArraySet> calss = (Class<CopyOnWriteArraySet>) list.getClass();
        Field field = calss.getDeclaredField("al");
        field.setAccessible(true);
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) field.get(list);

        Class<CopyOnWriteArrayList> calss2 = (Class<CopyOnWriteArrayList>) copyOnWriteArrayList.getClass();
        Field field2 = calss2.getDeclaredField("array");
        field2.setAccessible(true);
        Object[] objects = (Object[]) field2.get(copyOnWriteArrayList);

        System.out.println("list的容量：" + objects.length);
        for (int i = 0; i < objects.length; i++) {
            System.out.println("第"+(i+1)+"个元素：" + objects[i]);
        }
    }
}
