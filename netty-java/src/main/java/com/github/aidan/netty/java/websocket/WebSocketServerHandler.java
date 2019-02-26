package com.github.aidan.netty.java.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.netty.handler.codec.http.HttpHeaderUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaderUtil.setContentLength;

/**
 * @author wang yi fei
 * @date 2019/2/23 11:11
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = Logger.getLogger(WebSocketServerHandler.class.getName());
    private WebSocketServerHandshaker handshaker;


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {

        //传统HTTP 接入
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx,(FullHttpRequest)msg);
        }
        //WebSocket 接入
        else if (msg instanceof WebSocketFrame){
            handleWebsocketFrame(ctx,(WebSocketFrame)msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req){
        //如果HTTP 解码失败,返回HTTP异常
        if (!req.decoderResult().isSuccess()|| (!"websocket".equals(req.headers().get("Upgrade")))){
            sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        //构造握手响应返回,本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket",null,false);
        handshaker = wsFactory.newHandshaker(req);

        if (handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else {
            handshaker.handshake(ctx.channel(),req);
        }
    }

    private void handleWebsocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame){

        //判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        //判断是否是Ping 消息
        if (frame instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //本历程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)){
            throw new UnsupportedOperationException(String.format("s% frame types not supported",frame.getClass().getName()));
        }
        //返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        if (logger.isLoggable(Level.FINE)){
            logger.fine(String.format("s% received s%", ctx.channel(),request));
        }
        ctx.channel().write(new TextWebSocketFrame(request + " ,欢迎使用Netty Websocket 服务，现在时刻："+ new Date().toString()));
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res){
        //返回应答给客户端
        if (res.status().code()!=200){
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
           setContentLength(res,res.content().readableBytes());
        }
        //如果是非Keep-Alive,关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (isKeepAlive(req)|| res.status().code()!=200){
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel();
    }
}
