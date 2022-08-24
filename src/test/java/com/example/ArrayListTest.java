package com.example;

import org.apache.xmlbeans.InterfaceExtension;

import java.util.ArrayList;

/**
 * @description:
 * @author: chenliang
 * @create: 2022-05-28 11:27
 **/
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i=0;i<5;i++){
            arrayList.add("list->"+i);
        }
        for(String next : arrayList){
            if(next.equals("list->3")){
                arrayList.remove(next);
            }
            System.out.println(next);

        }
    }
}