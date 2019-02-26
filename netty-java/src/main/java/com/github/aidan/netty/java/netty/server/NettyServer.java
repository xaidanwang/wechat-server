package com.github.aidan.netty.java.netty.server;

import com.github.aidan.netty.java.netty.codec.NettyMessageDecoder;
import com.github.aidan.netty.java.netty.codec.NettyMessageEncoder;
import com.github.aidan.netty.java.netty.pojo.NettyConstant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import sun.security.krb5.internal.crypto.NullEType;

/**
 * @author wang yi fei
 * @date 2019/2/26 14:12
 */
public class NettyServer {


    public void bind() throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup =  new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new NettyMessageDecoder(1024*1024,4,4));
                        pipeline.addLast(new NettyMessageEncoder());
                        pipeline.addLast("readTimeoutHandler",new ReadTimeoutHandler(50));
                        pipeline.addLast(new LoginAuthRespHandler());
                        pipeline.addLast("HeartBeatHandler",new HeartBeatRespHandler());
                    }
                });
        //绑定端口，同步等待成功!
        b.bind(NettyConstant.REMOTEIP,NettyConstant.PORT).sync();
        System.out.println("Netty server start ok :"+(NettyConstant.REMOTEIP+ ":"+NettyConstant.PORT));
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyServer().bind();
    }
}
