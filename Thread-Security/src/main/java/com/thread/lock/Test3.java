package com.thread.lock;

        import java.util.concurrent.TimeUnit;
        import java.util.concurrent.locks.Lock;
        import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 98050
 * @Time: 2018-12-06 18:24
 * @Feature: tryLock(long time, TimeUnit unit)的使用
 */
public class Test3 {
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args)  {
        final Test3 test = new Test3();

        new Thread(){
            @Override
            public void run() {
                try {
                    test.insert(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    test.insert(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();
    }

    public void insert(Thread thread) throws InterruptedException {
        if (lock.tryLock(4, TimeUnit.SECONDS)){
            try {
                System.out.println(thread.getName()+"得到了锁");
                Thread.sleep(3000);
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                System.out.println(thread.getName()+"释放了锁");
                lock.unlock();
            }
        }else {
            System.out.println(thread.getName() + "获取锁失败");
        }
    }
}
