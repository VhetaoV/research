package com.example.demo.test;

public enum TestEnum {

    one(1,"2222"),
    two(3,"3333");



    private Integer code;
    private String msg;

    TestEnum() {
    }

    TestEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
