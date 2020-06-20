package com.example.demo.collection;


import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: hetao
 * @create: 2020-01-17 14:10
 **/
public class ListTest {

    public static void main(String[] agrs){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<16;i++){
            list.add(i);
        }
        int threadNum = 5;
        int perSize = list.size() / threadNum;
        int remainderSize = list.size() % threadNum;
        for (int i = 0; i <threadNum; i++) {
            int startIndex = i * perSize;
            int endIndex = (i + 1) * perSize;
            if (remainderSize != 0 && i == threadNum - 1) {
                endIndex = list.size();
            }

            for(Integer k : list.subList(startIndex, endIndex)){
                System.out.print(k);
            }
            System.out.println();
        }
    }
}
