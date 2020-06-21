package com.example.demo.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: hetao
 * @create: 2020-06-21 12:18
 **/
public class ThreadLocalAndPool {

    /**
     * jdk8 的语法
     */
    private static ThreadLocal<Integer> variableLocal = ThreadLocal.withInitial(() -> 0);

    public static int get() {
        return variableLocal.get();
    }

    public static void remove() {
        variableLocal.remove();
    }

    public static void increment() {
        variableLocal.set(variableLocal.get() + 1);
    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(2,2,0, TimeUnit.SECONDS,new LinkedBlockingDeque<>(12));
        for(int i=0;i<5;i++){
            executorService.execute(()->{
                long threadId = Thread.currentThread().getId();
                int before = get();
                increment();
                int after = get();
                System.out.println("threadid " + threadId +"  before " + before + ", after " + after);
                remove();
            });
        }
        executorService.shutdown();
    }

}
