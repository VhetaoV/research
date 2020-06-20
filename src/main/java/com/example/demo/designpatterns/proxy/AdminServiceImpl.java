package com.example.demo.designpatterns.proxy;

/**
 * @description:
 * @author: hetao
 * @create: 2020-01-21 09:26
 **/
public class AdminServiceImpl implements AdminServiceInter{

    @Override
    public void update() {
        System.out.println("修改管理系统数据");
    }

    @Override
    public Object find() {
        System.out.println("查看管理系统数据");
        return new Object();
    }
}
