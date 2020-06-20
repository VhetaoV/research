package com.example.demo.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description:JDK动态代理
 * @author: hetao
 * @create: 2020-01-21 09:54
 **/
public class AdminServiceInvocation implements InvocationHandler {

    private  Object target;

    public AdminServiceInvocation(Object target){
          this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("判断用户是否有权限进行操作");
        Object object = method.invoke(target,args);
        System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
        return object;
    }
}
