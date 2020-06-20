package com.example.demo.common;

/**
 * @description:
 * @author: hetao
 * @create: 2020-04-22 15:17
 **/
public class Person{

    private int id;
    private String userName;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Person(int id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
