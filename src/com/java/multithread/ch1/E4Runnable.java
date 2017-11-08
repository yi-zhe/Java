package com.java.multithread.ch1;

/**
 * 可以使用Runnable来构造一个Thread而不通过继承的方式
 */
public class E4Runnable implements Runnable {

    @Override
    public void run() {
        System.out.println("运行中!");
    }

    public static void main(String[] args) {
        Runnable runnable = new E4Runnable();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("运行结束!");
    }
}
