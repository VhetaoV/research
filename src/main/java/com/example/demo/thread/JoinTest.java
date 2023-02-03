package com.example.demo.thread;


import lombok.SneakyThrows;

/**
 * @description:
 * @author: chenliang
 * @create: 2023-01-03 17:30
 **/
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "线程运行开始!");
        new Runnable(){
            @SneakyThrows
            @Override
            public void run() {
                test();
            }
        };
        System.out.println("这时thread1执行完毕之后才能执行主线程");
    }

    public static void test() throws InterruptedException {
        Thread6 thread6 = new Thread6();
        thread6.setName("线程A");
        thread6.join();
        Thread7 thread7 = new Thread7();
        thread7.setName("线程B");
        thread7.start();
    }

}



class Thread6 extends Thread{

    @Override
    public void run() {
        System.out.println("子线程6运行");
    }
}

class Thread7 extends Thread{

    @Override
    public void run() {
        System.out.println("子线程7运行");
    }
}