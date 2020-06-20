package com.example.demo.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: hetao
 * @create: 2020-01-21 10:00
 **/
public class AdminServiceDynamicProxy {

    private Object target;

    private InvocationHandler invocationHandler;

    public AdminServiceDynamicProxy(Object target,InvocationHandler invocationHandler){
          this.target = target;
          this.invocationHandler = invocationHandler;
    }

    public Object getPersonProxy(){
        Object object = Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),invocationHandler);
        return object;
    }

}
