package com.thread.security;

/**
 * @Author: 98050
 * @Time: 2018-12-04 13:23
 * @Feature: volatile 保证共享变量的可见性
 */
public class Test007 {
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo7 threadDemo7 = new ThreadDemo7();
        threadDemo7.start();
        Thread.sleep(3000);
        threadDemo7.setRuning(false);
        System.out.println("flag已经改完false");
        Thread.sleep(1000);
        System.out.println("flag:"+threadDemo7.flag);
    }
}


class ThreadDemo7 extends Thread{
    public  boolean flag = true;

    @Override
    public void run() {
        System.out.println("线程开始.....");
        while (flag){

        }
        System.out.println("线程结束.....");
    }

    public void setRuning(boolean flag){
        this.flag = flag;
    }

}