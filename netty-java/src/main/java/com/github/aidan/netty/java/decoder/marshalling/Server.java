package com.github.aidan.netty.java.decoder.marshalling;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author wang yi fei
 * @date 2019/2/20 9:28
 */
public class Server {

    public void  bind(int port){

        EventLoopGroup boosGroup =  new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup,workGroup)
                    //指定NIO的模式，如果是客户端就是NioSocketChannel
                    .channel(NioServerSocketChannel.class)
                    //TCP 缓冲区设置
                    .option(ChannelOption.SO_BACKLOG,1024)
                    ////设置发送缓冲的大小
                    .option(ChannelOption.SO_SNDBUF,32*1024)
                    //设置接收缓冲区大小
                    .option(ChannelOption.SO_RCVBUF,32*1024)
                    //保持连续
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //设置Marshalling的编码和解码
                            ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                            ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                            ch.pipeline().addLast(new ServertHandler());
                        }
                    });

            ChannelFuture f = b.bind(port).sync();

            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;

        new Server().bind(port);
    }
}
