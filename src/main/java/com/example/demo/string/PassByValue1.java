package com.example.demo.string;

/**
 * 值传递  java不存在引用传递
 */
public class PassByValue1 {

    public static void main(String[] args){

        begin();
    }

    public static void begin(){
        StringBuffer stringBuffer = new StringBuffer("hello");
        changeStrBuff(stringBuffer);
        System.out.println(stringBuffer);
    }

    public static  void changeStrBuff(StringBuffer stringBuffer){
        stringBuffer = new StringBuffer("java");
        stringBuffer = stringBuffer.append("world");
        System.out.println(stringBuffer);
    }



}
