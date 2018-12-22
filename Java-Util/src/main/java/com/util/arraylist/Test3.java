package com.util.arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 98050
 * @Time: 2018-12-21 19:37
 * @Feature: toArray
 */
public class Test3 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        String[] strings = (String[]) list.toArray(new String[list.size()]);
    }
}
