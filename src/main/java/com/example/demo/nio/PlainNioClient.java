package com.example.demo.nio;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @description:
 * @author: hetao
 * @create: 2020-02-24 15:49
 **/
public class PlainNioClient {

    public void start(){
        try(SocketChannel socketChannel = SocketChannel.open()){
            //连接服务端socket
            SocketAddress socketAddress = new InetSocketAddress("localhost",8888);
            socketChannel.connect(socketAddress);

            int sendCount = 0;
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //这里最好使用selector
            while (sendCount < 10){

            }



        }catch (Exception e){
           e.printStackTrace();
        }
    }
}
