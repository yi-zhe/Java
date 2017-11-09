package com.java.multithread.ch1;

public class E7ThreadInterrupt extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100000; i++) {
            System.out.println("i=" + (i + 1));
        }
    }

    public static void main1(String[] args) {
        try {
            E7ThreadInterrupt threadInterrupt = new E7ThreadInterrupt();
            threadInterrupt.start();
            Thread.sleep(1000);

            threadInterrupt.interrupt();
            // Test whether thread main has interrupted
            System.out.println("Is stopped 1? = " + Thread.interrupted());
            System.out.println("Is stopped 2? = " + Thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end!");
    }

    public static void main2(String[] args) {
        Thread.currentThread().interrupt();
        System.out.println("Is stopped 1? = " + Thread.interrupted());
        // Thread#interrupted can clear the interrupt flag
        System.out.println("Is stopped 2? = " + Thread.interrupted());
        System.out.println("end!");
    }

    public static void main(String[] args) {
        try {
            E7ThreadInterrupt threadInterrupt = new E7ThreadInterrupt();
            threadInterrupt.start();
            Thread.sleep(1000);
            threadInterrupt.interrupt();
            System.out.println("Is stopped 1? = " + threadInterrupt.isInterrupted());
            System.out.println("Is stopped 2? = " + threadInterrupt.isInterrupted());
        } catch (InterruptedException e) {
            System.out.println("main catch!");
            e.printStackTrace();
        }

        System.out.println("end!");
    }
}
