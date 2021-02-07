package com.example.demo.voliate;

/**
 * @description: 测试多线程环境下指令重排
 * @author: chenliang
 * @create: 2021-02-07 12:39
 **/
public class volatileTest{

    public static void main(String[] args) {
        A instance = new A();

        Atest atest = new Atest(instance);
        atest.run();
        Btest btest = new Btest(instance);
        btest.run();
    }

}
class Atest implements Runnable{


    private A instance;

    public Atest(A instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        instance.methodA();
    }
}

class Btest implements Runnable{

    private A instance;

    public Btest(A instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        while(instance.a != 4){
            Thread.yield();
        }
        System.out.print(instance.b);
    }
}

class A {
    public int a;
    public boolean b = false;

    public void methodA(){
        a = 3;
        b = true;
        a = a + 1;
    }

    public void methodB(){
        a = 3;
        b = (a == 4);
        a = a + 1;
        System.out.println("a===="+a);
    }
}


