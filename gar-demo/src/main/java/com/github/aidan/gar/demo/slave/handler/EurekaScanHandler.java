package com.github.aidan.gar.demo.slave.handler;

import com.github.aidan.gar.demo.pojo.ComResponse;
import com.github.aidan.gar.demo.pojo.auth.User;
import com.github.aidan.gar.demo.shedule.EurekaScan;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.EventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wang yi fei
 * @date 2019/3/1 16:58
 */
@Slf4j
public class EurekaScanHandler extends ChannelHandlerAdapter {

    private volatile ScheduledFuture<?> scheduledTask;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ComResponse response = (ComResponse)msg;

        if ("login".equalsIgnoreCase(response.getTopic())&&response.getResult() == 0){
            //登陆成功开启定时任务定时查询本地的eureka 服务,并推送
            scheduledTask = ctx.executor().scheduleAtFixedRate(new EurekaScan(ctx),0,300000, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
