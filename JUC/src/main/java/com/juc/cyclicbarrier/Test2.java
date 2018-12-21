package com.juc.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: 98050
 * @Time: 2018-12-20 21:52
 * @Feature: CyclicBarrier的使用
 */
public class Test2 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 3; i++) {
            Write write = new Write(cyclicBarrier);
            write.start();
        }
    }
}
class Write extends Thread{

    private CyclicBarrier cyclicBarrier;

    public Write(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("线程"+Thread.currentThread().getName() + ",正在写入数据");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+Thread.currentThread().getName() + ",数据写入成功");

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"继续执行");
    }
}
