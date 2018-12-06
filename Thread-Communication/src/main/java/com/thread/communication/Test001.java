package com.thread.communication;

/**
 * @Author: 98050
 * @Time: 2018-12-05 23:19
 * @Feature:
 */

/**
 * 共享对象
 */
class Res{

    public String name;
    public String sex;

    /**
     * true 允许读，不能写
     * false 允许写，不能读
     */
    public Boolean flag = false;

}

/**
 * 写入线程
 */
class InThread extends Thread{

    public Res res;

    public InThread(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            synchronized (res) {
                if (res.flag){
                    try {
                        res.wait(); //释放当前锁对象
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (count == 0) {
                    res.name = "李四";
                    res.sex = "女";
                } else {
                    res.name = "王五";
                    res.sex = "男";
                }
                count = (count + 1) % 2;
                res.flag = true; //标记当前线程为等待
                res.notify(); //唤醒被等待的线程
            }
        }
    }
}

/**
 * 读取线程
 */
class OutThread extends Thread{

    public Res res;

    public OutThread(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (res) {
                try {
                    if (!res.flag){
                        res.wait();
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("姓名：" + res.name + "," + "性别：" + res.sex);
                res.flag = false;//标记当前线程为等待
                res.notify(); //唤醒被等待的线程
            }
        }
    }
}

public class Test001 {

    public static void main(String[] args) {
        Res res = new Res();
        InThread inThread = new InThread(res);
        OutThread outThread = new OutThread(res);
        inThread.start();
        outThread.start();
    }
}
