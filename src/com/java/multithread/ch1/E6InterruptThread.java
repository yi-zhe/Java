package com.java.multithread.ch1;

/**
 * Thread#interrupt并不能立即打断线程的执行, 只是打了一个停止标记
 */
public class E6InterruptThread extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            System.out.println("i=" + i);
        }
    }

    public static void main(String[] args) {
        try {
            E6InterruptThread thread = new E6InterruptThread();
            thread.start();
            thread.interrupt();
        } catch (Exception e) {
            System.out.println("main cache");
        }
    }
}
