package com.example.demo.threadlocal;

import java.nio.ByteBuffer;

import static javafx.scene.input.KeyCode.M;

/**
 * @description:
 * @author: hetao
 * @create: 2020-06-20 16:43
 **/
public class ThreadLocal2 {

    public static void main(String[] args){
        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024*10);
            ThreadLocal<ByteBuffer> threadLocal = new ThreadLocal<ByteBuffer>();
            threadLocal.set(byteBuffer);
            System.out.println(threadLocal.get());
            threadLocal = null;
        }
    }
}
