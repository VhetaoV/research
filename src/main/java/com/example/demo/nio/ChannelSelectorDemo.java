package com.example.demo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description: 读取某个文件
 * @author: hetao
 * @create: 2020-02-25 11:33
 **/
public class ChannelSelectorDemo {

    public static void main(String[] args) throws IOException {
        // 随机流关联文件,可读可写
        RandomAccessFile file = new RandomAccessFile("D:\\电视直播源\\电视频道.txt", "rw");
        FileChannel fileChannel = file.getChannel(); //文件使用该通道
        ByteBuffer buffer = ByteBuffer.allocate(1024); //Channel中的数据包装

        int byteRead = fileChannel.read(buffer);
        while (byteRead != -1) {
            System.out.print(byteRead);
            buffer.flip(); // 方法改变读写指针的位置,从0开始

            // hasRemaining()方法返回Buffer中剩余的可用长度
            while (buffer.hasRemaining()) {
                System.out.print((char)buffer.get());
            }

            buffer.clear(); //清空缓冲区
            byteRead = fileChannel.read(buffer);
        }

        file.close();
    }
}
