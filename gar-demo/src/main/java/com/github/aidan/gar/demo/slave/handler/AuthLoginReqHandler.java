package com.github.aidan.gar.demo.slave.handler;

import com.github.aidan.gar.demo.pojo.ComRequest;
import com.github.aidan.gar.demo.pojo.ComResponse;
import com.github.aidan.gar.demo.pojo.auth.User;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wang yi fei
 * @date 2019/3/1 15:33
 */
public class AuthLoginReqHandler extends ChannelHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush(buildLoginUser());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ComResponse response = (ComResponse)msg;

        if ("login".equalsIgnoreCase(response.getTopic())&&response.getResult() == 0){
            //登陆成功开启定时任务定时查询本地的eureka 服务,并推送
            ctx.fireChannelRead(msg);

        }else {
            //登陆失败，关闭客户端
            ctx.close();
        }
    }

    private ComRequest<User> buildLoginUser(){
        ComRequest<User> comRequest = new ComRequest<>();
        User user = new User();
        user.setPassword("123456");
        user.setUsername("aidanWang");
        comRequest.setData(user);
        comRequest.setMethod("request");
        comRequest.setTopic("login");
        comRequest.setTransactionId("1111111111111111");
        return comRequest;
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        ctx.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.fireExceptionCaught(cause);
        ctx.close();
    }


}
