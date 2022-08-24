package com.example;

import com.example.demo.common.Person;
import org.assertj.core.error.uri.ShouldHaveHost;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: chenliang
 * @create: 2022-05-28 22:51
 **/
public class ObjectCloneTest {


    public static void main(String[] args) {
        Object[] o = new Object[5];
        o[0] = new HashMap<>();
        Object[] oclone = o.clone();
        oclone[0] = new Object();
        System.out.println(o == oclone);
    }
}