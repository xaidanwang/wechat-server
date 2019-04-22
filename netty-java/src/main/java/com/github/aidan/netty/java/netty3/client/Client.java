package com.github.aidan.netty.java.netty3.client;

import com.github.aidan.netty.java.netty3.NettyConstant;
import com.github.aidan.netty.java.netty3.codec.NettyMessageDecoder;
import com.github.aidan.netty.java.netty3.codec.NettyMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wang yi fei
 * @date 2019/3/1 10:22
 */
public class Client {

    public void connect(String host,int port) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        EventLoopGroup group  = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4, -8, 0));
                            ch.pipeline().addLast(new NettyMessageEncoder());
                            ch.pipeline().addLast(new ReadTimeoutHandler(50));
                            ch.pipeline().addLast(new LoginAuthReqHandler());
                            ch.pipeline().addLast(new HeartBeatReqHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port)).sync();
            System.out.println("client: connect to server host:" + host + ", port:" + port);
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        //重连
                        connect(NettyConstant.REMOTEIP, NettyConstant.PORT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        new Client().connect(NettyConstant.REMOTEIP, NettyConstant.PORT);
    }
}
