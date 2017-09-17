package com.java.net.ch3;

import java.util.Random;
import java.util.concurrent.*;

public class E9FindMaxTask implements Callable<Integer> {

    private int data[];
    private int start;
    private int end;

    private E9FindMaxTask(int data[], int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (data[i] > max) max = data[i];
        }
        return max;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // initialize the data
        int data[] = new int[1000];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt();
            System.out.print(data[i]);
            System.out.print(" ");
            if (i != 0 && i % 50 == 0) System.out.println();
        }
        System.out.println();

        // divide the task into 2 parts
        E9FindMaxTask task1 = new E9FindMaxTask(data, 0, data.length / 2);
        E9FindMaxTask task2 = new E9FindMaxTask(data, data.length / 2, data.length);

        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);

        System.out.println(Math.max(future1.get(), future2.get()));
    }
}
