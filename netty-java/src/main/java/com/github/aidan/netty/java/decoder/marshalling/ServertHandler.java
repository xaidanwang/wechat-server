package com.github.aidan.netty.java.decoder.marshalling;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wang yi fei
 * @date 2019/2/20 9:38
 */
public class ServertHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Send send = (Send) msg;
        System.out.println("client发送："+send);

        Receive receive = new Receive();
        receive.setId(send.getId());
        receive.setMessage(send.getMessage());
        receive.setName(send.getName());
        ctx.writeAndFlush(receive);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
