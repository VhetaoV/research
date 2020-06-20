package com.example.demo.lock;

/**
 * synchronized修饰实例方法,当前线程的锁是实例对象accountingSync
 * 当一个线程正在访问一个对象的synchronized实例方法，那么其他线程不能访问该对象的其他synchronized方法
 * 一个对象只有一把锁
 */
public class AccountingSync implements Runnable{

    static AccountingSync accountingSync = new AccountingSync();
    //共享资源
    static int i = 0;
    static int j = 0;

    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for(int i =0;i<1000000;i++){
            synchronized (this){
                increase();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(accountingSync);
        Thread thread2 = new Thread(accountingSync);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}
