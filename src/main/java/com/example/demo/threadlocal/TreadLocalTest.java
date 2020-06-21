package com.example.demo.threadlocal;

import org.springframework.util.MultiValueMap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: hetao
 * @create: 2020-06-20 20:08
 **/
public class TreadLocalTest {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        MultiTest multiTest = new MultiTest();
        for(int i = 0;i<10;i++){
            executorService.submit(multiTest);
        }
    }

}

class MultiTest implements Runnable{

    ThreadLocal threadLocal = new ThreadLocal();

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getId()+":"+threadLocal.hashCode());
        threadLocal = null;
    }
}