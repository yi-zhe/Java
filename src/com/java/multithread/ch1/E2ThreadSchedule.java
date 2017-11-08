package com.java.multithread.ch1;

/**
 * 线程调度, 当线程sleep时候回让CPU调度其他线程
 */
public class E2ThreadSchedule extends Thread {

    @Override
    public void run() {
        super.run();
        threadPrint(10, "run");
    }

    public static void main(String[] args) {

        E2ThreadSchedule threadSchedule = new E2ThreadSchedule();
        threadSchedule.start();
        threadPrint(10, "main");
    }

    private static void threadPrint(int times, String name) {
        try {
            for (int i = 0; i < times; i++) {
                Thread.sleep((int) (Math.random() * 1000));
                System.out.println(name + "=" + Thread.currentThread());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
