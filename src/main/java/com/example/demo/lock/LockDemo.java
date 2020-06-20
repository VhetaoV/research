package com.example.demo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description::Lock的四种用法
 * @author: hetao
 * @create: 2020-03-15 19:07
 **/
public class LockDemo {

    public static void main(String[] args){
//        testlock();
//        testtry();
//        testinterrupt();
        testdelay();
    }

    /**
     * 普通用法
     */
    public static void testlock() {
        Lock lock = new ReentrantLock();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(1000);
                    System.out.println("goon");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        t.start();
        System.out.println("start");
        lock.lock();
        System.out.println("over");
        lock.unlock();
    }

    /**
     * lock.tryLock()
     */
    public static void testtry() {
        Lock lock = new ReentrantLock();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                lock.lock();
                System.out.println("get");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("release");
                }
            }

        });

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    if (lock.tryLock()) {
                        System.out.println("get success");
                        lock.unlock();
                        break;
                    }else {
                        System.out.println("get faile ... ");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        });
        t.start();
        t1.start();
    }

    /**
     * lock.lockInterruptibly();
     */
    public static void testinterrupt() {
        Lock lock = new ReentrantLock();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(10000);
                    System.out.println("goon ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    System.out.println("get ...");
                    lock.unlock();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                    System.out.println("interrupt ... ");
                }
            }

        });

        t.start();
        t1.start();

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("to interrupt ");
        t1.interrupt();
    }

    /**
     *
     */
    public static void testdelay() {
        Lock lock = new ReentrantLock();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                lock.lock();
                try {
                    Thread.sleep(10000);
                    System.out.println("goon ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(lock.tryLock(10, TimeUnit.SECONDS)) {
                        System.out.println("get ...");
                        lock.unlock();
                    }else {
                        System.out.println("have not get  ...");
                    }
                } catch (InterruptedException e1) {
                    //e1.printStackTrace();
                    System.out.println("interrupt ... ");
                }
            }
        });
        t.start();
        t1.start();
    }
}


