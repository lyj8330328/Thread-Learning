package com.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: 98050
 * @Time: 2018-12-13 17:27
 * @Feature: 读写锁
 */
public class Test003 {

    private volatile Map<String,String> cache = new HashMap<String, String>();

    /**
     * 读写锁
     */
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    /**
     * 写入锁
     */
    private ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
    /**
     * 读取锁
     */
    private ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    /**
     * 写入元素
     * @param key
     * @param value
     */
    public  void put(String key,String value){
        try{
            writeLock.lock();
            System.out.println("写入put方法key:"+key+",value："+value+",开始");
            cache.put(key, value);
            Thread.sleep(300);
            System.out.println("写入put方法key:"+key+",value："+value+",结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * 读取元素
     * @param key
     * @return
     */
    public  String get(String key){

        try {
            readLock.lock();
            System.out.println("读取key:"+key+",开始");
            Thread.sleep(300);
            String value = cache.get(key);
            System.out.println("读取key:"+key+",结束");
            return value;
        } catch (Exception e) {
            return null;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        final Test003 test003 = new Test003();

        /**
         * 写入线程
         */
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    test003.put(i+"", i+"");
                }
            }
        });

        /**
         * 读取线程1
         */
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName()+"获取数据：" + test003.get(i+""));
                }
            }
        });

        /**
         * 读取线程2
         */
        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "获取数据：" + test003.get(i+""));
                }
            }
        });


        thread.start();
        thread2.start();
        thread3.start();
    }

}
