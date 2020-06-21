package com.example.demo.threadlocal;

/**
 * @description:
 * @author: hetao
 * @create: 2020-06-20 14:27
 **/
public class threadlocal {

    private static A a = new A();
    private static final ThreadLocal<A> threadLocal = new ThreadLocal<A>(){
        protected A initialValue(){
            return a;
        }
    };

    public static void main(String[] args){
        Thread[] threads = new Thread[5];
        for(int i = 0;i < 5;i++ ){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    threadLocal.get().setNumber(threadLocal.get().getNumber() + 5);
                    System.out.println(Thread.currentThread().getName() + ":"+
                    threadLocal.get().getNumber());
                }
            },"Thread-"+i);
        }
        for(Thread thread : threads){
            thread.start();
        }

    }
}



class A{
    private int number = 0;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}