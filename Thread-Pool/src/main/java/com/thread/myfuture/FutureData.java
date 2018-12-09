package com.thread.myfuture;

/**
 * @Author: 98050
 * @Time: 2018-12-07 23:06
 * @Feature:
 */
public class FutureData implements Data {

    public volatile static boolean FLAG = false;
    private RealData realData;

    public synchronized void setRealData(RealData realData){
        //如果已经获取到结果，直接返回
        if (FLAG){
            return;
        }
        //如果没有获取到数据，传递真实对象
        this.realData = realData;
        FLAG = true;
        notify();
    }

    public synchronized String getRequest() {
        while (!FLAG){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //获取到数据，直接返回
        return realData.getRequest();
    }
}
