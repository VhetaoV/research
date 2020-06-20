package com.example.demo.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @description:
 * @author: hetao
 * @create: 2019-12-24 16:37
 **/
public class Producer implements Runnable{

    private ConcurrentLinkedQueue queue;

    public Producer(ConcurrentLinkedQueue queue){
          this.queue = queue;
    }

    public void run(){
        int i=1;
        while (true){
            if(queue.size()<5){
                String data = "Producer===="+i;
                i++;
                queue.offer(data);
                System.out.println("生产者产生数据："+data);
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
