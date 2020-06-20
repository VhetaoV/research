package com.example.demo.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @description:
 * @author: hetao
 * @create: 2020-01-16 14:53
 **/
public class Consumer implements Runnable{

    private ConcurrentLinkedQueue queue;

    public Consumer(ConcurrentLinkedQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            if(!queue.isEmpty()){
                String data = queue.poll().toString();
                System.err.println("消费者"+Thread.currentThread().getName()+"消费："+data);
                try{
                    Thread.sleep(10);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                try{
                    Thread.sleep(10);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
