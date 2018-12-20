package com.github.aidan.adsl.server.socket;

import com.github.aidan.adsl.server.util.RemoteShellTool2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class RefreshBatch {

    public String execute(RemoteShellTool2 tool){

        String result ="";
        ExecutorService pool = Executors.newCachedThreadPool();
        FutureTask<String> refreshFuture = new FutureTask<String>(new RefreshThread(tool));
        pool.submit(refreshFuture);
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)){
                pool.shutdownNow();
                result = "拨号卡死,重启vps输入5";
            }
            if (refreshFuture.isDone() && refreshFuture.get()!=null){
                result = "刷新成功";
            }
        }catch (Exception e){
            e.printStackTrace();
            pool.shutdownNow();
            result ="线程执行异常";
        }
        return result;
    }
}
