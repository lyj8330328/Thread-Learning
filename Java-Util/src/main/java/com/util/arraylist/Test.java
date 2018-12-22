package com.util.arraylist;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 98050
 * @Time: 2018-12-21 14:31
 * @Feature: 查看容量
 */
public class Test {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 11; i++) {
            list.add(i+"");
        }
        Class<List> calss = (Class<List>) list.getClass();
        Field field = calss.getDeclaredField("elementData");
        field.setAccessible(true);
        Object[] objects = (Object[]) field.get(list);
        System.out.println(objects.length);
    }

}
