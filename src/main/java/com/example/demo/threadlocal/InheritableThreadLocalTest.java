package com.example.demo.threadlocal;

import static java.lang.String.format;

/**
 * @description:
 * @author: chenliang
 * @create: 2023-03-08 18:13
 **/
public class InheritableThreadLocalTest {

    public static final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        inheritableThreadLocal.set("My Inheritable");
        threadLocal.set("threadLocal");
        System.out.println(format("当前线程Inheritable值：%s", inheritableThreadLocal.get()));
        System.out.println(format("当前线程值：%s", threadLocal.get()));

        new Thread(() -> {
            System.out.println(format("子线程Inheritable值：%s", inheritableThreadLocal.get()));
            System.out.println(format("子线程值：%s", threadLocal.get()));
            new Thread(() -> {
                System.out.println(format("孙子线程Inheritable值：%s", inheritableThreadLocal.get()));
                System.out.println(format("孙子线程值：%s", threadLocal.get()));
                new Thread(() -> {
                    System.out.println(format("曾子线程Inheritable值：%s", inheritableThreadLocal.get()));
                    System.out.println(format("曾子线程值：%s", threadLocal.get()));
                }).start();

            }).start();

        }).start();
    }
}