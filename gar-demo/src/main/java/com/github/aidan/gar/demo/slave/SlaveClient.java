package com.github.aidan.gar.demo.slave;

import com.github.aidan.gar.demo.pojo.Constant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wang yi fei
 * @date 2019/3/1 14:42
 */
public class SlaveClient {




    public void connect(String host,int port) throws InterruptedException {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                        }
                    });

            ChannelFuture future = bootstrap.connect(new InetSocketAddress(host,port)).sync();

            future.channel().closeFuture().sync();
        }finally {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        //重连
                        connect(Constant.REMOTEIP, Constant.PORT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new SlaveClient().connect(Constant.REMOTEIP, Constant.PORT);
    }
}
