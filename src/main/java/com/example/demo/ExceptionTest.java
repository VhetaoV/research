package com.example.demo;

import java.math.BigDecimal;

/**
 * @description:
 * @author: hetao
 * @create: 2020-01-03 11:21
 **/
public class ExceptionTest {

    public static void main(String[] agrs)throws Exception{
        BigDecimal a = BigDecimal.valueOf(0);
        a = new BigDecimal(1);
        if(a.equals(BigDecimal.ZERO)){
            throw new Exception();
        }
        a = new BigDecimal(2);
     System.out.println(a);
    }
}
