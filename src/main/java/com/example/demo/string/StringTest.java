package com.example.demo.string;

import java.util.UUID;

/**
 * @description: Test
 * @author: hetao
 * @create: 2019-08-20 20:54
 **/
public class StringTest {

    public static void main(String[] args){

        String a = new String("aaa");
        String b = "aaa";
        String c = "a"+"aa";
        String d = "a"+new String("aa");

        System.out.println(a==b);
        System.out.println(b==c);
        System.out.println(a==c);
        System.out.println(a==d);
        System.out.println(UUID.randomUUID().toString().replaceAll("-","").substring(0,6));
    }
}
