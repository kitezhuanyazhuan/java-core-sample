package com.oops;

import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ExecutorService executors = new ThreadPoolExecutor(2,Integer.MAX_VALUE,30,TimeUnit.SECONDS,new SynchronousQueue());
        executors.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("=====shahs");
            }
        });
    }
}
