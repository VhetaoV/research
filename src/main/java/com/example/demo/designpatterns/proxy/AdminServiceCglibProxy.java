package com.example.demo.designpatterns.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: hetao
 * @create: 2020-01-21 11:08
 **/
public class AdminServiceCglibProxy implements MethodInterceptor {

    private Object target;

    public AdminServiceCglibProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        //工具类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return  enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("判断用户是否有权限进行操作");
        Object obj = method.invoke(target);
        System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
        return obj;
    }
}
