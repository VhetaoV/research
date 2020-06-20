package com.example.demo.lock;

/**
 * @description:修饰静态方法,锁是当前类的 class对象，进入同步代码前要获得当前类对象的锁　
 * @author: hetao
 * @create: 2020-03-15 14:47
 **/
public class AccountingSyncClass implements Runnable{

    static int i = 0;

    /**
     * synchronized作用于静态方法，锁是当前class对象
     */
    public static synchronized void increase() {
        i++;
    }

    /**
     *  increase4Obj方法是实例方法，其对象锁是当前实例对象，
     *  如果别的线程调用该方法，将不会产生互斥现象，毕竟锁对象不同，
     *  但我们应该意识到这种情况下可能会发现线程安全问题(操作了共享静态变量i)。
     */
    public synchronized void increase4Obj(){
        i++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
//            increase();
            increase4Obj();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        //new新实例
        Thread thread1 = new Thread(new AccountingSyncClass());
        //new新实例
        Thread thread2 = new Thread(new AccountingSyncClass());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}
