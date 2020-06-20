package com.example.demo.common;

/**
 * @description:
 * @author: hetao
 * @create: 2020-04-22 15:17
 **/
public class Person2 implements Comparable<Person2>{

    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person2(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person2 person2){
        //按照年龄正序
        //return this.getAge() - person2.getAge();
        //按照年龄进行排序 (并且是倒序)
        return person2.getAge() - this.getAge();
    }
}
