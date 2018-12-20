package com.thread.falsesharing;

/**
 * @Author: 98050
 * @Time: 2018-12-19 23:25
 * @Feature: cache line特性
 */
public class CacheLineEffect {

    private static long[][] result;

    public static void main(String[] args) {
        int row =1024 * 1024;
        int col = 8;
        result = new long[row][];
        for (int i = 0; i < row; i++) {
            result[i] = new long[col];
            for (int j = 0; j < col; j++) {
                result[i][j] = i+j;
            }
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = 0;
            }
        }
        System.out.println("使用cache line特性，循环时间：" + (System.currentTimeMillis() - start));

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                result[j][i] = 1;
            }
        }
        System.out.println("没有使用cache line特性，循环时间：" + (System.currentTimeMillis() - start2));

    }
}
