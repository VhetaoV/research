package com.example.demo.string;

public class PassByValue4 {

    public  static  void main(String[] args){
        //在main方法的栈中创建引用s和引用s1，此引用s和引用s1存放在栈（main方法的栈）中；
        // 编译时，在常量池中创建两个常量"hello"和"world"，s和s1分别指向两个常量
        String s = "hello";
        String s1 = "world";
        System.out.println(s+"---"+s1);//1:hello---world
        change(s,s1);//引用s和s1作为参数传递到change方法中
        //change方法中的引用s,s1和main方法中的引用s,s1存放地址并不同，以下输出的是main方法栈中的s和s1，
        // 并没有发生变化
        System.out.println(s+"---"+s1);//3:hello---world


        //以下两行代码将会在main方法栈中创建引用sb和sb1，并在堆内存中创建两个对象"hello"和"world",
        // sb和sb1分别指向两个对象
        StringBuilder sb = new StringBuilder("hello");
        StringBuilder sb1 = new StringBuilder("world");
        System.out.println(sb+"---"+sb1);//4:hello---world
        change(sb,sb1);//引用sb和sb1作为参数传递到change方法中
        // main方法中的sb所指向的堆内存地址未发生变化，故仍为"hello"，
        // 而change(sb,sb1)方法改变了main方法中sb1所指向的堆内存地址的内容，
        // 故代码6有以下输出
        System.out.println(sb+"---"+sb1);//6:hello---worldworld
    }

    public static void change(String s,String s1){
        s = s1;//此时将引用s指向s1所指向的常量池中"world"
        s1 = s+s1;//此时并非在常量池而是在堆内存中创建"worldworld"对象，并将s1指向此堆内存地址
        System.out.println("change(s,s1)---"+s+"---"+s1);//2:change(s,s1)---world---worldworld
    }

    public static void change(StringBuilder sb, StringBuilder sb1) {
        //在change方法的栈(和上面的change方法栈不同)中创建引用sb和sb1,
        // 并通过句柄的形式指向main方法栈中sb和sb1所指向的对象
        sb = sb1;//将引用sb指向sb1所引用的对象"world"
        sb1.append(sb);//拼接字符串，此时引用sb1所指向的对象发生变化，变为"worldworld"，
        // 此时外部main方法中的sb1和此方法中的sb均指向此堆内存地址,
        // 此地址内容发生变化后，外部main方法中的sb1指向的内容也跟着变化
        System.out.println("change(sb,sb1)---"+sb+"---"+sb1);//5:change(sb,sb1)---worldworld---worldworld  }
    }


}
