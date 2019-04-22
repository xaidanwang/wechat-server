package com.github.aidan.java8.test.schedule;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wang yi fei
 * @date 2019/3/30 10:34
 */
public class ScheduledExecutorServiceTest {
    public static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(15,new BasicThreadFactory.Builder().namingPattern("scheduled-pool-%s").daemon(true).build());

    public static void main(String[] args) {

        scheduledExecutorService.scheduleAtFixedRate(new Thread(new TestRunable(),"定时任务1"),3000L,1000L, TimeUnit.MILLISECONDS);
/*        scheduledExecutorService.scheduleAtFixedRate(new Thread(new TestRunable(),"定时任务2"),0,3000L, TimeUnit.MILLISECONDS);


        scheduledExecutorService.scheduleAtFixedRate(new TestRunable2("定时任务3"),0,5000L, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new TestRunable2("定时任务4"),0,3000L, TimeUnit.MILLISECONDS);*/
        while (true){

        }
    }
}
