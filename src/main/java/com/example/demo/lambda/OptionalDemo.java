package com.example.demo.lambda;

import com.example.demo.common.Person;
import com.example.demo.test.TestEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Optional;

/**
 * @description:
 * @author: chenliang
 * @create: 2020-12-16 19:58
 **/
@Slf4j
public class OptionalDemo {


    public static void main(String[] args) {
//        Person person = new Person(1,"呵呵",19);
//        Optional<Integer> age = getAge(person);
//        System.out.println(age.get());

//        Optional.of("has value").orElse(getDefault()); //do invoke 会执行
//        Optional.of("has value").orElseGet(() -> getDefault()); // 不会执行
//
//        System.out.println(TestEnum.one.ordinal());

        System.out.println(readFileLength("ww"));
    }


    public static Long readFileLength(String fileName) {
        try {
            return 0L;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            return 1L;
        }
    }

    public static void getName(Person person){
        Optional.ofNullable(person).ifPresent(p -> System.out.println(p.getUserName()));
    }

    public static void filterAge(Person person)
    {
        Optional.ofNullable(person).filter( u -> u.getAge() > 18).ifPresent(u ->  System.out.println("The student age is more than 18."));
    }


    public static Optional<Integer> getAge(Person person)
    {
        return Optional.ofNullable(person).map(u -> u.getAge());
    }


    public static Optional<Integer> getAge2(Person person)
    {
        return Optional.ofNullable(person).flatMap(u -> Optional.ofNullable(u.getAge()));
    }

    public static String getDefault() {
        System.out.println("do invoke");
        return "default";
    }
}