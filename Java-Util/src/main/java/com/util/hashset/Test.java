package com.util.hashset;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: 98050
 * @Time: 2018-12-23 15:08
 * @Feature: HashSet遍历
 */
public class Test {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 1000000; i++) {
            set.add(i);
        }
        ThroughIterator(set);
        ThroughForEach(set);

    }

    public static void ThroughIterator(Set set){
        Iterator iterator = set.iterator();
        long start = System.currentTimeMillis();
        while (iterator.hasNext()){
            iterator.next();
        }
        System.out.println("使用迭代器耗时：" + (System.currentTimeMillis() - start));
    }

    public static void ThroughForEach(Set set){
        long start = System.currentTimeMillis();
        for (Object o : set.toArray()){
            ;
        }
        System.out.println("使用For-Each耗时：" + (System.currentTimeMillis() - start));
    }
}
