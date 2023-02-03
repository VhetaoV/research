package com.example.demo.test;

import java.math.BigDecimal;

/**
 * @description: BigDecimal测试类
 * @author: chenliang
 * @create: 2023-01-29 13:55
 **/
public class TestBigDecimal {


    public static void main(String[] args) {
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        System.out.println(bigDecimal.divide(BigDecimal.valueOf(3),2,BigDecimal.ROUND_HALF_UP));
    }
}