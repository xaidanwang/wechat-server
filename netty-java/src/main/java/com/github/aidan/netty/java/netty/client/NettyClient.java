package com.github.aidan.netty.java.netty.client;

import com.github.aidan.netty.java.netty.codec.NettyMessageDecoder;
import com.github.aidan.netty.java.netty.codec.NettyMessageEncoder;
import com.github.aidan.netty.java.netty.pojo.NettyConstant;
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

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);



    public void connect(int port,String host) throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();
        try {

            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new NettyMessageDecoder(1024*1024,4,4));
                    pipeline.addLast("MessageEncoder",new NettyMessageEncoder());
                    pipeline.addLast("readTimeoutHandler",new ReadTimeoutHandler(50));
                    pipeline.addLast("LoginAuthHandler",new LoginAuthReqHandler());
                    pipeline.addLast("HeartBeatHandler",new HeartBeatReqHandler());
                }
            });
            //发起异步连接操作
            ChannelFuture future = b.connect(new InetSocketAddress(host,port),new InetSocketAddress(NettyConstant.LOCALIP,NettyConstant.LOCAL_PORT)).sync();

        }finally {
            //释放所有资源完成之后,清空资源，再次发起重连操作
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(5);

                        try {
                            //发起重连操作
                            connect(NettyConstant.PORT,NettyConstant.REMOTEIP);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new NettyClient().connect(NettyConstant.PORT,NettyConstant.REMOTEIP);
    }

}
