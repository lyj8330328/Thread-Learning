package com.thread.security;

/**
 * @Author: 98050
 * @Time: 2018-12-05 22:45
 * @Feature: 重排序对多线程的影响
 */
public class Test009 {

    int a = 0;

    boolean flag = false;

    /**
     * 写入线程
     */
    public void writer(){
        a = 1;  //1
        flag = true;  //2
    }

    /**
     * 读取线程
     */
    public void reader(){
        if (flag){ //3
            int i = a*a; //4
        }
    }
}
