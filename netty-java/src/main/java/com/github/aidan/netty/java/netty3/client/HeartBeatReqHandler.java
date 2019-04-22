package com.github.aidan.netty.java.netty3.client;

import com.github.aidan.netty.java.netty3.NettyType;
import com.github.aidan.netty.java.netty3.pojo.Header;
import com.github.aidan.netty.java.netty3.pojo.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wang yi fei
 * @date 2019/3/1 10:52
 */
public class HeartBeatReqHandler extends ChannelHandlerAdapter {

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        final NettyMessage message = (NettyMessage) msg;
        //握手成功，主动发送心跳
        if (message.getHeader() != null && message.getHeader().getType() == NettyType.LOGIN_AUTH_RESP.value) {
            heartBeat = ctx.executor().scheduleAtFixedRate(
                    new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000L, TimeUnit.MILLISECONDS
            );
        } else if (message.getHeader() != null && message.getHeader().getType() == NettyType.BEAT_HEART_RESP.value) {
            System.out.println("client: receive HEARTBEAT_RESP ---> " + message);
        } else {
            ctx.fireChannelRead(message);
        }
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.close(ctx, promise);
    }

    private class HeartBeatTask implements Runnable {
        private ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage message = buildHeartBeat();
            System.out.println("client: send HeartBeat to server --->" + message);
            ctx.writeAndFlush(message);
        }

        private NettyMessage buildHeartBeat() {
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            header.setType(NettyType.BEAT_HEART_RRP.value);
            message.setHeader(header);
            message.setBody((byte) 0);
            return message;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }
}
