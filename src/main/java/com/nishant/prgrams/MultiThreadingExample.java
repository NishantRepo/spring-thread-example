package com.nishant.prgrams;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadingExample {

    public static void main(String[] args) {
        add();
    }

    public static void add() {

        ExecutorService executorService = Executors.newFixedThreadPool(5);


        Callable<Integer> callable = () -> {
            System.out.println(Thread.currentThread().getName());
            return 1+3;
        };

        Future<Integer> future = executorService.submit(callable);
        Future<Integer> future1 = executorService.submit(callable);
        Future<Integer> future2 = executorService.submit(callable);
        Future<Integer> future3= executorService.submit(callable);
        Future<Integer> future4 =executorService.submit(callable);
        Future<Integer> future5 = executorService.submit(callable);


        try {
            System.out.println(future.get());

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
        System.out.println(future4.get());
        System.out.println(future5.get());

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }
}
