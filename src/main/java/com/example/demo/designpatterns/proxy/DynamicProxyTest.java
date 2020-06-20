package com.example.demo.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: JDK动态代理
 * @author: hetao
 * @create: 2020-01-21 10:14
 **/
public class DynamicProxyTest {

    public static void main(String[] args){
        // 方法一
        System.out.println("============ 方法一 ==============");
        AdminServiceInter adminServiceInter1 = new AdminServiceImpl();
        System.out.println("代理的目标对象：" + adminServiceInter1.getClass());
        AdminServiceInvocation adminServiceInvocation = new AdminServiceInvocation(adminServiceInter1);
        AdminServiceInter proxy = (AdminServiceInter) new AdminServiceDynamicProxy(adminServiceInter1, adminServiceInvocation).getPersonProxy();
        System.out.println("代理对象：" + proxy.getClass());
        Object obj = proxy.find();
        System.out.println("find 返回对象：" + obj.getClass());
        System.out.println("----------------------------------");
        proxy.update();




        //方法二
        System.out.println("============ 方法二 ==============");
        AdminServiceInter adminServiceInter2 = new AdminServiceImpl();
        AdminServiceInvocation invocation = new AdminServiceInvocation(adminServiceInter2);
        AdminServiceInter proxy2 = (AdminServiceInter) Proxy.newProxyInstance(adminServiceInter2.getClass().getClassLoader(), adminServiceInter2.getClass().getInterfaces(), invocation);
        Object obj2 = proxy2.find();
        System.out.println("find 返回对象：" + obj2.getClass());
        System.out.println("----------------------------------");
        proxy2.update();



        //方法三
        System.out.println("============ 方法三 ==============");
        final AdminServiceInter adminServiceInter3 = new AdminServiceImpl();
        AdminServiceInter proxy3 = (AdminServiceInter) Proxy.newProxyInstance(adminServiceInter3.getClass().getClassLoader(), adminServiceInter3.getClass().getInterfaces(), new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("判断用户是否有权限进行操作");
                Object obj = method.invoke(adminServiceInter3, args);
                System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
                return obj;
            }
        });
        Object obj3 = proxy3.find();
        System.out.println("find 返回对象：" + obj3.getClass());
        System.out.println("----------------------------------");
        proxy3.update();
    }

}
