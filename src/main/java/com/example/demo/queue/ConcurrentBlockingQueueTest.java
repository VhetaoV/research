package com.example.demo.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @description:
 * @author: hetao
 * @create: 2019-12-24 16:36
 **/
public class ConcurrentBlockingQueueTest {


     public static void main(String[] args){
         ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
         new Thread(new Producer(concurrentLinkedQueue)).start();
         new Thread(new Consumer(concurrentLinkedQueue)).start();
         new Thread(new Consumer(concurrentLinkedQueue)).start();
     }

}
