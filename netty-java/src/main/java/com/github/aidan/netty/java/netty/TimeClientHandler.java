package com.github.aidan.netty.java.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * @author wang yi fei
 * @date 2019/2/15 10:48
 */
public class TimeClientHandler extends ChannelHandlerAdapter{
    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

  //  private final ByteBuf firstMessage;

    private int counter;
    private byte[] req;

    public TimeClientHandler() {

        req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
/*        byte[] req = "QUERY TIME ORDER ".getBytes();
        //初始化ByteBuf 的大小
        firstMessage = Unpooled.buffer(req.length);
        //将req 字节流写入  ByteBuf 缓存中
        firstMessage.writeBytes(req);*/
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ByteBuf message = null;
        for (int i = 0 ; i< 100 ;i++){
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
        //当服务端TCP 链路建立成功之后，Netty 的NIO 线程会调用channelActice 方法，发送查询指令给服务端,
        //调用ChannelHandlerContext.writeAndFlush 方法将请求发送给服务端
      //  ctx.writeAndFlush(firstMessage);

        // super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"utf-8");
        System.out.println("Now is :"+ body+" ; the counter is :"+ ++counter);

        // super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //释放资源
        logger.warning("Unexpected exception from downstream :"+cause.getMessage());
        ctx.close();
        //super.exceptionCaught(ctx, cause);
    }
}
