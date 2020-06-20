package com.example.demo.designpatterns.proxy;

/**
 * @description:
 * @author: hetao
 * @create: 2020-01-21 09:28
 **/
public class AdminServiceProxy implements AdminServiceInter {

    private AdminServiceInter adminServiceInter;

    public AdminServiceProxy(AdminServiceInter adminServiceInter) {
        this.adminServiceInter = adminServiceInter;
    }

    @Override
    public void update() {
        System.out.println("判断用户是否有权限进行update操作");
        adminServiceInter.update();
        System.out.println("记录用户执行update操作的用户信息、更改内容和时间等");
    }

    @Override
    public Object find() {
        System.out.println("判断用户是否有权限进行find操作");
        System.out.println("记录用户执行find操作的用户信息、查看内容和时间等");
        return adminServiceInter.find();
    }
}