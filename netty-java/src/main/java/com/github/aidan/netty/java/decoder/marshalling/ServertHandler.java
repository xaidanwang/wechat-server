package com.github.aidan.netty.java.decoder.marshalling;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.udt.nio.NioUdtMessageRendezvousChannel;

/**
 * @author wang yi fei
 * @date 2019/2/20 9:38
 */
public class ServertHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Send send = (Send) msg;

        if (send.getData() instanceof Car){
            System.out.println("接收数据car");
            System.out.println("client发送："+send);
        }
        if (send.getData() instanceof  People){
            System.out.println("接收数据people");
            System.out.println("client发送："+send);
        }
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
