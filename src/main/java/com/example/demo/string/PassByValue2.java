package com.example.demo.string;

/**
 * 值传递  java不存在引用传递
 */
public class PassByValue2 {

    public static void main(String[] args){
        StringBuffer a = new StringBuffer("aaa");
        StringBuffer b = new StringBuffer("bbb");
        operate(a,b);
        System.out.println(a+"----"+b);
    }

    public static void operate(StringBuffer x,StringBuffer y) {

        x.append(y);//操作的是对象
        y=x;//改变的只是形参的值

    }

}
