package com.example.demo.constructor;

/**
 * @description: 测试构造方法初始化过程
 * @author: hetao
 * @create: 2019-08-20 21:25
 **/
public class Test1 {
    public static  void main(String[] args){
       Child child = new Child();
       child = new Child();
    }
}

class Father{
    static {
        System.out.println("father static");
    }
    public Father(){
        System.out.println("father construct");
    }
}

class  Child extends Father{
    static {
        System.out.println("Child static");
    }
    public Child(){
        System.out.println("Child construct");
    }
}