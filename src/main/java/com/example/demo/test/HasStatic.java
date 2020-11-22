package com.example.demo.test;

/**
 * @description:
 * @author: chenliang
 * @create: 2020-10-21 15:27
 **/
public class HasStatic {

    private static int x=100;
    public static void main(String args[]){
          HasStatic hs1=new HasStatic();
          hs1.x++;
          HasStatic  hs2=new HasStatic();
          hs2.x++;
          hs1=new HasStatic();
          hs1.x++;
          HasStatic.x--;
          System.out.println("x="+x);
     }
}