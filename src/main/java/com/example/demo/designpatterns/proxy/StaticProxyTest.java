package com.example.demo.designpatterns.proxy;

/**
 * @description: 静态代理测试
 * @author: hetao
 * @create: 2020-01-21 09:31
 **/
public class StaticProxyTest {

    public static void main(String[] args){
        AdminServiceInter adminServiceInter = new AdminServiceImpl();
        AdminServiceProxy adminServiceProxy = new AdminServiceProxy(adminServiceInter);
        adminServiceProxy.update();
        System.out.println("=============================");
        adminServiceProxy.find();
    }
}
