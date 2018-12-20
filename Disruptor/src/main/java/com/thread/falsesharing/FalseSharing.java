package com.thread.falsesharing;

/**
 * @Author: 98050
 * @Time: 2018-12-20 12:06
 * @Feature: 伪共享
 */
public class FalseSharing implements Runnable {

    /**
     * 线程数
     */
    public static int NUM_THREADS = 4;
    /**
     * 迭代次数
     */
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;
    private static VolatileLong[] longs;
    public static long SUM_TIME = 0L;

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i){
            longs[arrayIndex].value = i;
        }
    }

    @sun.misc.Contended
    public final static class VolatileLong {
        public volatile long value = 0L;
        //public long p1, p2, p3, p4, p5, p6; //缓存行填充
    }

    private static void runTest() throws InterruptedException {
        Thread[] thread = new Thread[NUM_THREADS];
        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : thread){
            t.start();
        }
        for (Thread t : thread){
            t.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            if (args.length == 1){
                NUM_THREADS = Integer.parseInt(args[0]);
            }
            longs = new VolatileLong[NUM_THREADS];
            for (int j = 0; j < longs.length; j++) {
                longs[j] = new VolatileLong();
            }
            final long start = System.nanoTime();
            runTest();
            final long end = System.nanoTime();
            SUM_TIME += end - start;
        }
        System.out.println("平均耗时：" + SUM_TIME / 10);
    }
}
