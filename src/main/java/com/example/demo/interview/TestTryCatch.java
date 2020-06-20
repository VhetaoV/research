package com.example.demo.interview;

public class TestTryCatch {

    public  static  int  test(){
        int i = 3;
        int j = 2;
        try{
            System.out.println("try代码块");
            return i+j;
        }catch (Exception e){
            System.out.println("catch代码块");
        }finally {
            System.out.println("finally代码块");
        }
        return 0;
    }

    public static void main(String[] args){
        System.out.println(test());
    }


}
