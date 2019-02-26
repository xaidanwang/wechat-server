package com.github.aidan.netty.java.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author wang yi fei
 * @date 2019/2/23 11:06
 */
public class WebSocketServer {

    public void  run(int port) throws InterruptedException {

        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HttpServerCodec());
                            ch.pipeline().addLast(new HttpObjectAggregator(65536));
                            ch.pipeline().addLast(new ChunkedWriteHandler());
                            ch.pipeline().addLast(new WebSocketServerHandler());
                        }
                    });

            ChannelFuture f = b.bind(port).sync();

            System.out.println("Web socket server started at port "+ port +".");
            System.out.println("Open your browser and navigate to http://localhost: "+ port +"/");
            f.channel().closeFuture().sync();

        }finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;

        new WebSocketServer().run(port);
    }


}
