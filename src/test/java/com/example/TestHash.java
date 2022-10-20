package com.example;

import java.util.*;

/**
 * @description: 1407374883553280000 47
 * @author: chenliang
 * @create: 2022-09-29 15:54
 **/
public class TestHash {

    public static void main(String[] args) {
//        Random random = new Random();
//        List<Long> list = new ArrayList<>();
//        Map<Long,List<Long>> skuMap = new HashMap<>();
//        int arrayLength = 20;
//        for(long i=0;i<100*10000;i++){
//            list.add(i);
//        }
//        //1000000000000
//        for(long j=100000000000L;j<100000000000L+100000;j++){
//            list.add(j);
//        }
//        for (long sku : list){
//            long index = sku % arrayLength;
//            if(skuMap.get(index) == null){
//                List<Long> splitList = new ArrayList<>();
//                skuMap.put(index,splitList);
//            }
//            skuMap.get(index).add(sku);
//        }
        System.out.println(Long.MAX_VALUE / 10000);
    }




    public static void calSplitIndex(){
        Map<Map<Long,Long>,Long> indexMap = new HashMap<>();
        long index=0;
       for(long i=10000;i<Long.MAX_VALUE && i>0;){
           Map<Long,Long> segmentMap = new HashMap<>();
           if(i == 10000){
                 segmentMap.put(0L,10000L);
             }else{
               segmentMap.put(i,i+10000);
           }
           index++;
           indexMap.put(segmentMap,index);
       }
       System.out.println(indexMap);
    }

    public static void calSplitIndex1(){
        Map<Long,Long> indexMap = new HashMap<>();
        List<Long> skuList = new ArrayList<>();
        for(long i=0;i<Long.MAX_VALUE;i++){
            skuList.add(i);
        }
        for(Long sku : skuList){
            long index = sku % 50;
            indexMap.put(index,sku);
        }
        System.out.println(indexMap);
    }
}