package com.example.demo.string;

public class StringJVM {

    public static void change(String s, String s1) {

        //在change方法的栈中创建引用s和s1,并指向常量池中的常量  s = s1;
        // 此时将引用s指向s1所指向的常量池中"world"  s1 = s+s1;
        // 此时并非在常量池而是在堆内存中创建"worldworld"对象，并将s1指向此堆内存地址
        // System.out.println("change(s,s1)---"+s+"---"+s1);//2:change(s,s1)---world---worldworld
        //      }
    }

    public  static void main(){

    }
}
