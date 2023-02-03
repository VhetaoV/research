package com.example.demo.thread;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ext.liuhuayong1
 * @description: 有关线程池的配置
 * @date 2022/5/7 16:02
 */
public class ExecutorServiceUtil {
    /**核心线程数*/
    //private static Integer corePoolSize=100;
    /**最大线程数*/
    //private static Integer maximumPoolSize=Integer.MAX_VALUE;
    /**空闲线程存活时间*/
    private static Long KEEPALIVETIME=10L;
    /**列表的容量*/
    private static Integer CAPACITY=3000;


    static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NamedThreadFactory(String poolName) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = poolName+ "-" +poolNumber.getAndIncrement()+
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }



    /**
     * ThreadPoolExecutor.AbortPolicy: CallerRunsPolicy 丢弃任务并抛出RejectedExecutionException异常。AbortPolicy
     * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。 DiscardPolicy
     * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，执行后面的任务，不抛出异常。 DiscardOldestPolicy
     * ThreadPoolExecutor.CallerRunsPolicy：由调用线程池的线程（比如main线程）处理该任务 ，不抛出异常，没有丢弃任务。 CallerRunsPolicy
     * corePoolSize : 核心线程数
     * maximumPoolSize : 最大线程数
     * */

    public static ExecutorService getThreadPoolExecutor(Integer corePoolSize,Integer maximumPoolSize,RejectedExecutionHandler allocateExecutionHandler){
        //若未指定策略的情况下,默认给出不抛弃策略;
        if(null==allocateExecutionHandler){
            allocateExecutionHandler=new ThreadPoolExecutor.CallerRunsPolicy();
        }
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        };
        ThreadFactory threadFactory1 = runnable->{
           return new Thread(runnable);
        };

        ThreadFactory threadFactory2 = runable->{
          return new Thread(runable,"hhbbb");//没有线程编号
        };

        ThreadFactory threadFactory3 = Thread::new;

        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                KEEPALIVETIME, TimeUnit.SECONDS, new LinkedBlockingQueue<>(CAPACITY),new NamedThreadFactory("product-base-thread-pool"),allocateExecutionHandler);
        return threadPoolExecutor;
    }

    static ThreadLocal<SimpleDateFormat>threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };


    public static void main(String[] args) {
        ExecutorService executorService = getThreadPoolExecutor(100,200, null);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(threadLocal.get().parse("2019-10-22 16:59:00"));
                    throw new NullPointerException("11111111");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        ExecutorService executorService2 = getThreadPoolExecutor(100,200, null);

        executorService2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(threadLocal.get().parse("2019-10-22 16:59:00"));
                    throw new NullPointerException("22222222");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}


