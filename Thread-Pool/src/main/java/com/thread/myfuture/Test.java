package com.thread.myfuture;

/**
 * @Author: 98050
 * @Time: 2018-12-07 23:17
 * @Feature:
 */
public class Test {

    public static void main(String[] args) {
        FutureClient futureClient = new FutureClient();
        final Data request = futureClient.request("请求参数........");
        System.out.println("请求发送成功！");

        String result = request.getRequest();
        System.out.println("获取到的结果："+result);
        System.out.println("继续执行其它任务");
    }
}
