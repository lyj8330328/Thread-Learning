package com.util.arraylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: 98050
 * @Time: 2018-12-21 19:12
 * @Feature: 遍历方式
 */
public class Test2 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 900000; i++) {
            list.add(i);
        }
        RandomAccess(list);
        ThroughIterator(list);
        ThroughFor(list);
    }

    public static void RandomAccess(List list){
        long start,end;
        start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("随机访问耗时：" + (end - start));
    }

    public static void ThroughIterator(List list){
        long start,end;
        start = System.currentTimeMillis();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next();
        }

        end = System.currentTimeMillis();
        System.out.println("使用迭代器耗时：" + (end - start));
    }

    public static void ThroughFor(List list){
        long start,end;
        start = System.currentTimeMillis();
        for (Object o : list){
            ;
        }
        end = System.currentTimeMillis();
        System.out.println("使用For耗时：" + (end - start));
    }
}
