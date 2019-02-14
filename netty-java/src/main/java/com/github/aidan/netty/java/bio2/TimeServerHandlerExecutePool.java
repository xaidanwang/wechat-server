package com.github.aidan.netty.java.bio2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wang yi fei
 * @date 2019/2/13 11:01
 */
public class TimeServerHandlerExecutePool {

    private ExecutorService executor;

    public TimeServerHandlerExecutePool(int maxPoolSieze,int queueSize){
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSieze,120L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize));
    }
    public void execute(Runnable task){
        executor.execute(task);
    }
}
