package com.thread.communication;

/**
 * @Author: 98050
 * @Time: 2018-12-06 14:17
 * @Feature:
 */
/**
 * 共享对象
 */
class Res2{

    public String name;
    public String sex;
}

/**
 * 写入线程
 */
class InThread2 extends Thread{

    public Res res;

    public InThread2(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (res) {
                if (count == 0) {
                    res.name = "李四";
                    res.sex = "女";
                } else {
                    res.name = "王五";
                    res.sex = "男";
                }
                count = (count + 1) % 2;
            }
        }
    }
}

/**
 * 读取线程
 */
class OutThread2 extends Thread{

    public Res res;

    public OutThread2(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (res) {
                System.out.println("姓名：" + res.name + "," + "性别：" + res.sex);
            }
        }
    }
}

public class Test002 {

    public static void main(String[] args) {
        Res res = new Res();
        InThread2 inThread = new InThread2(res);
        OutThread2 outThread = new OutThread2(res);
        inThread.start();
        outThread.start();
    }
}
