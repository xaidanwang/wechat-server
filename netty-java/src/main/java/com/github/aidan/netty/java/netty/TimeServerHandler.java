package com.github.aidan.netty.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.nio.ByteBuffer;

/**
 * @author wang yi fei
 * @date 2019/2/14 18:13
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    private  int counter;
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        //super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        /**
         * 半包发送演示，改造
         */
        //System.getProperty("line.separator") 获取当前系统的换行符的符号，
        // unix \n   Windows \n\r  mac \r
        String body = new String(req,"UTF-8").substring(0,req.length-System.getProperty("line.separator").length());
        System.out.println("The time server receiver order : "+ body + " ; the counter is :"+ ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new java.util.Date((System.currentTimeMillis())).toString(): "BAD ORDER";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);

/*        String body = new String(req,"UTF-8");
        System.out.println("The time server receiver order :"+body);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new java.util.Date((System.currentTimeMillis())).toString(): "BAD ORDER";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);*/
        //super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
       // super.channelReadComplete(ctx);
    }
}
