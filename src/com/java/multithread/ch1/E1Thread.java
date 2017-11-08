package com.java.multithread.ch1;

public class E1Thread extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println("E1Thread");
    }

    public static void main(String[] args) {
        E1Thread thread = new E1Thread();
        thread.start();
        System.out.println("运行结束!");
    }
}
