package com.java.multithread.ch1;

/**
 * 多线程访问同一个变量导致的线程安全问题
 * 解决办法 给run方法加同步锁synchronized
 */
public class E5MultiThreadAccessOneVar implements Runnable {

    private int count = 5;

    @Override
    public void run() {
        while (count-- > 0) {
            System.out.println("由" + Thread.currentThread() + "计算,count=" + count);
        }
    }

    public static void main(String[] args) {
        E5MultiThreadAccessOneVar thread = new E5MultiThreadAccessOneVar();
        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        Thread t3 = new Thread(thread);
        Thread t4 = new Thread(thread);
        Thread t5 = new Thread(thread);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
