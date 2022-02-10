package com.example.demo.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: hashmap测试
 * @author: chenliang
 * @create: 2022-02-01 16:37
 **/
public class HashMapTest {

    public static void main(String[] args) {
        Map<Integer,Integer> hashMap = new HashMap();
        Integer result = hashMap.put(1,1);
        System.out.println(result);
    }
}