package com.example.demo.designpatterns.proxy;

/**
 * @description:
 * @author: hetao
 * @create: 2020-01-21 11:14
 **/
public class CglibProxyTest {

    public static void main(String[] args) {

        AdminCglibService adminCglibService = new AdminCglibService();
        AdminServiceCglibProxy proxyFactory = new AdminServiceCglibProxy(adminCglibService);
        AdminCglibService proxy = (AdminCglibService)proxyFactory.getProxyInstance();

        System.out.println("代理对象：" + proxy.getClass());

        Object obj = proxy.find();
        System.out.println("find 返回对象：" + obj.getClass());
        System.out.println("----------------------------------");
        proxy.update();
    }
}
