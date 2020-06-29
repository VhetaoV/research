package com.example.demo.string;

public class StringInternDemo {


    public static void main(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("2") + new String("3");
        s3.intern();
        String s4 = "23";
        System.out.println(s3 == s4);
    }
}