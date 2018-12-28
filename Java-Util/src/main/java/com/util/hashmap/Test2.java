package com.util.hashmap;

import java.util.HashMap;

/**
 * @Author: 98050
 * @Time: 2018-12-28 15:04
 * @Feature:
 */
public class Test2 {

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<Integer, String>(16);
        map.put(7, "");
        map.put(11, "");
        map.put(43, "");
        map.put(59, "");
        map.put(19, "");
        map.put(3, "");
        map.put(35, "");

        System.out.println("遍历结果：");
        for (Integer key : map.keySet()) {
            System.out.print(key + " -> ");
        }

    }
}
