package com.oops.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 RUNNING 自然是运行状态，指可以接受任务执行队列里的任务
 SHUTDOWN 指调用了 shutdown() 方法，不再接受新任务了，但是队列里的任务得执行完毕。
 STOP 指调用了 shutdownNow() 方法，不再接受新任务，同时抛弃阻塞队列里的所有任务并中断所有正在执行任务。
 TIDYING 所有任务都执行完毕，在调用 shutdown()/shutdownNow() 中都会尝试更新为这个状态。
 TERMINATED 终止状态，当执行 terminated() 后会更新为这个状态。

 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        /**
         * ThreadPoolExecutor 执行策略
         *

         线程数量未达到corePoolSize，则新建一个线程(核心线程)执行任务

         线程数量达到了corePools，则将任务移入队列等待

         队列已满，新建线程(非核心线程)执行任务

         队列已满，总线程数又达到了maximumPoolSize，(RejectedExecutionHandler)抛出异常

         */
        Executor executor = new ThreadPoolExecutor(2,
                Integer.MAX_VALUE,
                30,
                TimeUnit.SECONDS,
                new SynchronousQueue());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ttt");
            }
        });
        /**
         * 参数
         * corePoolSize 核心线程
         * maximumPoolSize 最大线程 = 核心 + 非核心
         * keepAliveTime 非核心线程，空闲销毁时间
         */
        /**
         * BlockingQueue<Runnable> workQueue
         * 1:SynchronousQueue
         *  队列接收到任务的时候，会直接提交给线程处理，而不保留它。如果所有线程都在工作，就新建一个线程来处理这个任务。所以为了保证不出现<线程数达到了maximumPoolSize而不能新建线程>的错误，
         *  使用这个类型队列的时候，maximumPoolSize一般指定成Integer.MAX_VALUE，即无限大
         * 2:ArrayBlockingQueue
         *  可以限定队列的长度，接收到任务的时候，如果没有达到corePoolSize的值，则新建线程(核心线程)执行任务，如果达到了，则入队等候，如果队列已满，则新建线程(非核心线程)执行任务，
         *  如果总线程数到了maximumPoolSize，并且队列也满了，则发生错误
         * 3:DelayedWorkQueue
         *  队列内元素必须实现Delayed接口，这就意味着你传进去的任务必须先实现Delayed接口。这个队列接收到任务时，首先先入队，只有达到了指定的延时时间，才会执行任务
         * 4:LinkedBlockingQueue
         * 队列接收到任务的时候，如果当前线程数小于核心线程数，则新建线程(核心线程)处理任务；如果当前线程数等于核心线程数，则进入队列等待。由于这个队列没有最大值限制，
         * 即所有超过核心线程数的任务都将被添加到队列中，这也就导致了maximumPoolSize的设定失效，因为总线程数永远不会超过corePoolSize
         * 5:PriorityBlockingQueue
         */
    }
}
