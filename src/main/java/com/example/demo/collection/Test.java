package com.example.demo.collection;

import sun.misc.Unsafe;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public  static  void main(String[] args) throws Exception{
        Map<String, String> map = new HashMap<String, String>(19);
        Class<?> mapType1 = map.getClass();
        Method capacity1 = mapType1.getDeclaredMethod("capacity");
        System.out.println("capacity1 : " + capacity1.invoke(map));
        map.put("hahaha", "hollischuang");
        Class<?> mapType = map.getClass();
        Method capacity = mapType.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println("capacity : " + capacity.invoke(map));
    }
}
