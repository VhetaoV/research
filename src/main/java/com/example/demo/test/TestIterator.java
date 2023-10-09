package com.example.demo.test;

import jodd.util.StringUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: chenliang
 * @create: 2023-09-05 12:11
 **/
public class TestIterator {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        stringList.add("a");
        stringList.add("b");
        Iterator<String> stringIterator = stringList.iterator();
        while (stringIterator.hasNext()){
            if(StringUtil.equals("a",stringIterator.next())){
                stringIterator.remove();
            }
        }
        System.out.println(stringList);
    }
}