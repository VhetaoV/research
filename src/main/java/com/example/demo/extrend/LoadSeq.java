package com.example.demo.extrend;

class People {
    private String describe;
    //8，父类构造函数 .
    public People() { }
    //8，父类构造函数
    public People(String describe) {
        this.describe = describe;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    static {
        System.out.println("父类静态代码块");
    }
}
class User extends People {
    //1，成员无值属性
    private int age;
    //1，成员无值属性
    private String name;
    //2，成员有值属性
    private double hight = 1.8;
    //2，成员有值属性
    private double salary = 5000;
    //3，静态属性
    public static final String gender = "男";
    //6，有参构造函数
    public User(int age, double hight) {
        super();
        this.age = age;
        this.hight = hight;
    }
    //5，无参构造方法
    public User() {
        super();
    }
    //4，静态方法
    public static void getAllFileds() {
        System.out.println("属性有age;hight;gender");
    }
    //7，静态代码块
    static {
        System.out.println("子类静态代码块");
    }
}
public class LoadSeq {

    public static void main(String[] args) {
        User user = new User();
        User.getAllFileds();
        String gender = User.gender;
        System.out.println(gender);
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.get();
        User.getAllFileds();
        String gender1 = User.gender;
    }
}


/*
* 总结：1，对象加载时候如果有父类，并且父类有静态代码块，那么就先执行父类的静态代码块。

2，父类执行完毕，如果子类即本来有静态代码块，就先执行本类静态代码块。

3，静态代码块执行完毕，不论是有参还是无参构造函数就，如果有父类就先执行父类的构造方法，然后再加载本类的构造方法。

4，构造方法执行完毕之后，如果有赋值属性就优先走赋值属性。如果没有构造函数加载完毕返回对象。

5，至于静态属性和静态方法，Java中的静态方法在class文件被加载时就会分配内存，而非静态方法要在程序运行过程中类实例化后才会存在。
* */