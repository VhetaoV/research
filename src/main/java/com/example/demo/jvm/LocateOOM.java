package com.example.demo.jvm;

public class LocateOOM {

    public static void main(String[] args){
        int i = 0;
        String string = "str";
        while(true){
            i++;
            string+=i;
        }

    }
}
