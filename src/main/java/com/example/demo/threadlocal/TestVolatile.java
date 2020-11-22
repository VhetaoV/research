package com.example.demo.threadlocal;

/**
 * @description: 测试volatile
 * @author: chenliang
 * @create: 2020-11-12 09:33
 **/
public class TestVolatile {

    public static volatile boolean stop = false;

    public static void main(String[] args) {
//        loop();
//        stop();
    }


    public  static void loop() {
        while (!stop) { System.out.println("loop ....."); }
    }
    public static void stop() { stop = true; }
}