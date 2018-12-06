package com.thread.security;

/**
 * @Author: 98050
 * @Time: 2018-12-04 10:52
 * @Feature: ThreadLocal
 */
public class Test006 extends Thread {
    /**
     * ThreadLocal提高一个线程的局部变量，访问某个线程拥有自己局部变量。
     *  当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     *  所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */

    /**
     * ThreadLocal底层是一个Map集合
     * Map.put(“当前线程”,值)；
     */
    private Res res;

    public Test006(Res res){
        this.res = res;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName()+","+res.getNumber());
        }
    }

    public static void main(String[] args) {
        Res res = new Res();
        Test006 t1 = new Test006(res);
        Test006 t2 = new Test006(res);
        t1.start();
        t2.start();
    }
}

class Res{

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 0;
        }
    };

    public synchronized Integer getNumber(){
        int count = threadLocal.get() + 1;
        threadLocal.set(count);
        return count;
    }
}
