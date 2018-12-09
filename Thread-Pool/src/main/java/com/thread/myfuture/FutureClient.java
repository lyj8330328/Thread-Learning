package com.thread.myfuture;

/**
 * @Author: 98050
 * @Time: 2018-12-07 23:15
 * @Feature:
 */
public class FutureClient {

    public Data request(final String queryStr){
        final FutureData futureData = new FutureData();
        new Thread(new Runnable() {
            public void run() {
                try {
                    RealData realData = new RealData(queryStr);
                    futureData.setRealData(realData);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return futureData;
    }
}
