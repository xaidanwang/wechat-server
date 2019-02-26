package com.github.aidan.netty.java.netty.server;

import com.github.aidan.netty.java.netty.pojo.Header;
import com.github.aidan.netty.java.netty.pojo.MessageType;
import com.github.aidan.netty.java.netty.pojo.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wang yi fei
 * @date 2019/2/25 18:15
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.LOGIN_REQ
                .value()) {
            System.out.println("Receive client heart beat message : ---> "+message);
            NettyMessage heartBeat = buildHearBeat();
            System.out.println("Send hear beat response message to client --->"+heartBeat);
            ctx.writeAndFlush(heartBeat);
        }else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHearBeat(){
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }
}
