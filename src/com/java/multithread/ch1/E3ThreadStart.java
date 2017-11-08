package com.java.multithread.ch1;

/**
 * 调用Thread#start()的顺序不代表线程启动的顺序
 * 只是通知调度器已经准备好了, 但何时调度不知道
 */
public class E3ThreadStart extends Thread {
    private int i;
    public E3ThreadStart(int i) {
        super();
        this.i = i;
    }

    @Override
    public void run() {
        super.run();
        System.out.println(i);
    }

    public static void main(String[] args) {
        E3ThreadStart t1 = new E3ThreadStart(1);
        E3ThreadStart t2 = new E3ThreadStart(2);
        E3ThreadStart t3 = new E3ThreadStart(3);
        E3ThreadStart t4 = new E3ThreadStart(4);
        E3ThreadStart t5 = new E3ThreadStart(5);
        E3ThreadStart t6 = new E3ThreadStart(6);
        E3ThreadStart t7 = new E3ThreadStart(7);
        E3ThreadStart t8 = new E3ThreadStart(8);
        E3ThreadStart t9 = new E3ThreadStart(9);
        E3ThreadStart t10 = new E3ThreadStart(10);
        E3ThreadStart t11 = new E3ThreadStart(11);
        E3ThreadStart t12 = new E3ThreadStart(12);
        E3ThreadStart t13 = new E3ThreadStart(13);
        E3ThreadStart t14 = new E3ThreadStart(14);
        E3ThreadStart t15 = new E3ThreadStart(15);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();
        t13.start();
        t14.start();
        t15.start();
    }
}
