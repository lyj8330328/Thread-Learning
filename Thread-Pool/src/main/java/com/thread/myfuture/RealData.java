package com.thread.myfuture;

/**
 * @Author: 98050
 * @Time: 2018-12-07 23:07
 * @Feature:
 */
public class RealData implements Data {

    private String result;

    public RealData(String data) throws InterruptedException {
        System.out.println("正在使用data:"+data+"网络请求数据，耗时需要等待");
        Thread.sleep(3000);
        System.out.println("读取数据成功，获取结果。。。。。");
        result = "success";
    }

    @Override
    public String getRequest() {
        return result;
    }
}
