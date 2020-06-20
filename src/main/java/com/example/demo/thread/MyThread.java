package com.example.demo.thread;

/**
 * @description: 阿里面试题
 * @author: hetao
 * @create: 2019-08-20 20:38
 **/
public class MyThread implements Runnable {
    private int x;
    private int y;

    public static void main(String[] args) {
        MyThread that = new MyThread();
        (new Thread(that)).start();
        (new Thread(that)).start();
    }

    public synchronized void run() {
        x = x >= 10 ? (x + y) : x;
        y = y > 2 ? y * x : y;
        System.out.println("x=" + x++ + "y=" + ++y);
    }

    public void start() {
        x = 10;
        y = 10;
        run();
    }
}