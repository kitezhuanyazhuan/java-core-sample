package com.oops.concurrent;


import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class CommThreadPoolTest {

    /**
     * 可缓存线程池：
     *     线程数无限制
     *     有空闲线程则复用空闲线程，若无空闲线程则新建线程
     *     一定程序减少频繁创建/销毁线程，减少系统开销
     */
    @Test
    public void testCachedThreadPool() throws Exception{
        long start = System.currentTimeMillis();
        ExecutorService executors = Executors.newCachedThreadPool();
        executors.shutdown();
        /**
         * pool.awaitTermination(1, TimeUnit.SECONDS) 会每隔一秒钟检查一次是否执行完毕（状态为 TERMINATED），
         * 当从 while 循环退出时就表明线程池已经完全终止了。
         */
        while (!executors.awaitTermination(1, TimeUnit.SECONDS)) {
            LOGGER.info("线程还在执行。。。");
        }
        long end = System.currentTimeMillis();
        System.out.println("一共处理了【{}】"+(end - start));
    }

    /**
     * 定长线程池：
     *     可控制线程最大并发数（同时执行的线程数）
     *     超出的线程会在队列中等待
     */
    @Test
    public void testFixedThreadPool(){
//        ExecutorService executors = Executors.newFixedThreadPool(10);
        ExecutorService executors = Executors.newFixedThreadPool(10, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        });
    }

    /**
     * 定长线程池：
     *     支持定时及周期性任务执行。
     */
    @Test
    public void testsSheduledThreadPool(){
        String s = "";
        ExecutorService executorService = Executors.newScheduledThreadPool(10);
    }

    /**
     * 单线程化的线程池：
     *     有且仅有一个工作线程执行任务
     *     所有任务按照指定顺序执行，即遵循队列的入队出队规则
     */
    @Test
    public void testssingleThreadPool (){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }


}
