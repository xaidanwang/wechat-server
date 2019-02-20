package com.github.aidan.netty.java.decoder.marshalling;

import com.github.aidan.netty.java.decoder.TimeClient;
import com.github.aidan.netty.java.decoder.TimeClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author wang yi fei
 * @date 2019/2/20 9:40
 */
public class Client {

    public void connect(int port,String host) throws InterruptedException {

        //配置客户端NIO 线程组
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                                 @Override
                                 protected void initChannel(SocketChannel ch) throws Exception {
                                     //设置Marshalling的编码和解码
                                     ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                                     ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());

                                     ch.pipeline().addLast(new ClientHandler());
                                 }
                             }
                    );
            //发起异步连接操作
            ChannelFuture f = b.connect(host,port).sync();
            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        }finally {
            //优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;

        if (args != null && args.length > 0 ){
            port = Integer.valueOf(args[0]);
        }

        new Client().connect(port,"127.0.0.1");
    }
}
