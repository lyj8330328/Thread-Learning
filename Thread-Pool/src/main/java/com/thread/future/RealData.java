package com.thread.future;

import java.util.concurrent.Callable;

/**
 * @Author: 98050
 * @Time: 2018-12-07 23:07
 * @Feature:
 */
public class RealData implements Callable<String> {

    private String data;

    public RealData(String data)  {
        this.data = data;
    }

    @Override
    public String call() throws Exception {
        System.out.println("正在使用:"+data+"........请求数据，耗时等待中");
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+i);
            Thread.sleep(1000);
        }
        return "success";
    }
}
