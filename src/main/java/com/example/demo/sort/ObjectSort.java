package com.example.demo.sort;

import com.example.demo.common.Person;

import java.util.*;

/**
 * @description:
 * @author: hetao
 * @create: 2020-04-27 21:18
 **/
public class ObjectSort {

    public static void main(String[] args){

        List<Person> personList = new ArrayList<>();
        Person person1 = new Person(1,"123456",25);
        Person person2 = new Person(2,"123456",15);
        Person person3 = new Person(3,"123456",20);

        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

        //排序前
        if (personList != null && personList.size() > 0) {
            for (Person person : personList) {
                System.out.println(person);
            }
            System.out.println("-------------");
        }
        //排序
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                return person1.getAge() - person2.getAge();
            }
        });

        //倒序
        Collections.reverse(personList);

        //排序后
        if (personList != null && personList.size() > 0) {
            for (Person person : personList) {
                System.out.println(person);
            }
            System.out.println("-------------");
        }

    }
}
