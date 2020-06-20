package com.example.demo.basicType;

public class Test {

    public static void main(String[] args){
//        int a = 1;
//        float b = 2.91f;
//        System.out.println("a=a+b============="+(b=a+b));//类型提升为float
////        System.out.println("a=a+b============="+(a=a+b));报错类型不匹配
//        System.out.println("a+=b============="+(a+=b));//类型降级

        char i = '3';
        char j = '4';
        int sum = i + j - '0'*2;
        System.out.println(sum);
    }
}
