package com.example.demo.string;

/**
 * 值传递  java不存在引用传递
 */
public class PassByValue3 {

    public static void main(String[] args){
        String a = new String("aaaa");
        change(a);
        System.out.println(a);
}

    public static void change(String x) {

        x="xxxxx";//改变的只是形参的值

    }

}
