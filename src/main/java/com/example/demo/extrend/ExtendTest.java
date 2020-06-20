package com.example.demo.extrend;
/*
* 静态代码块>构造代码块（类中）>构造函数>普通代码块（方法中）
*
* */
public class ExtendTest {

    public static void main(String[] args){
        FatherClass s = new ChildClass();
        s.out();
        System.out.println(s.i+"--"+s.j);
    }
}

abstract  class BaseClass{
    int i;
    int j;
    BaseClass(){
        System.out.println("B0");
    }
    abstract void out();
}
class FatherClass extends BaseClass{
    int i=0;
    int j=3;
    FatherClass(){
        System.out.println("F0");
    }
    void out(){
        System.out.println(i+" "+j);
    }
    static {
        System.out.println("F1");
    }
    {
        System.out.println("F2");
    }
}
class ChildClass extends FatherClass{
    int i=3;
    int j=0;
    ChildClass(){
        System.out.println("C0");
    }
    void out(){
        System.out.println(i+" "+j);
    }
    static {
        System.out.println("C1");
    }
    {
        System.out.println("C2");
    }
}
