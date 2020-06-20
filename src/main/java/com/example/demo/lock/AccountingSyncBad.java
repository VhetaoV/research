package com.example.demo.lock;

/**
 * thread1访问实例对象obj1的synchronized方法，thread2访问实例对象obj1的synchronized方法
 * 这样是允许的，因为两个实例对象锁并不相同。
 * 此时如果两个线程操作数据非共享，线程安全有保证，如果数据共享，线程安全无法保证
 *
 */
public class AccountingSyncBad implements Runnable{

    static int i = 0;

    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        //new新实例
        Thread thread1 = new Thread(new AccountingSyncBad());
        //new新实例
        Thread thread2 = new Thread(new AccountingSyncBad());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }

}
