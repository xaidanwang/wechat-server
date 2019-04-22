package com.github.aidan.netty.java.netty.client;

import com.github.aidan.netty.java.netty.codec.NettyMessageDecoder;
import com.github.aidan.netty.java.netty.codec.NettyMessageEncoder;
import com.github.aidan.netty.java.netty.pojo.NettyConstant;
import com.github.aidan.netty.java.netty2.pojo.NettyConstants;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wang yi fei
 * @date 2019/2/26 9:43
 */
public class NettyClient {

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    EventLoopGroup group = new NioEventLoopGroup();

    public void connect(int port, String host) {

        //client NIO thread
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
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

            //异步连接
            ChannelFuture future = b.connect(new InetSocketAddress(host, port)).sync();
            System.out.println("client: connect to server host:" + host + ", port:" + port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放资源，重连
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        //重连
                        connect(NettyConstants.REMOTE_PORT, NettyConstants.REMOTE_IP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }


    public static void main(String[] args) {
        new NettyClient().connect(NettyConstants.LOCAL_PORT, NettyConstants.REMOTE_IP);
    }

}
