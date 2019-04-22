package com.github.aidan.netty.java.netty.client;

import com.github.aidan.netty.java.netty.pojo.Header;
import com.github.aidan.netty.java.netty.pojo.MessageType;
import com.github.aidan.netty.java.netty.pojo.NettyMessage;
import com.google.protobuf.Extension;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wang yi fei
 * @date 2019/2/25 11:28
 */
@Slf4j
public class LoginAuthReqHandler extends ChannelHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("发送信息："+buildLoginReq());
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage)msg;

        //如果是握手请求，需要判断是否认证成功
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()){
            byte loginResult = (Byte)message.getBody();
            if (loginResult != (byte) 0){
                //握手失败
                ctx.close();
            }else {
                System.out.println("Login is ok:" +message);
                ctx.fireChannelRead(message);
            }
        }else {
            ctx.fireChannelRead(message);
        }
    }
    private NettyMessage buildLoginReq(){
         NettyMessage message = new NettyMessage();
         Header header = new Header();
         header.setType((byte)3);
         message.setHeader(header);
         message.setBody((byte) 0);
         return message;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
