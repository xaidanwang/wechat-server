package com.github.aidan.netty.java.decoder.msgpack;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author wang yi fei
 * @date 2019/2/16 20:09
 */
public class EchoServer {

    public void bind(int port) throws InterruptedException {

        //NioEventLoopGroup 是一个线程组，包含了一组NIO线程
        //两个对象，一个用于服务端接收客户端的连接，一个用于进行SocketChannel的网络读写。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            //ServerBootstrap 对象 是Netty 用于启动NIO服务端的辅助启动类
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new MsgpackDecoder());
//                            ch.pipeline().addLast(new MsgpackEncoder());
//                            ch.pipeline().addLast(new EchoServerHandler());
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,0,
                                    2,0,2));
                            ch.pipeline().addLast(new MsgpackDecoder());
                            ch.pipeline().addLast(new LengthFieldPrepender(2));
                            ch.pipeline().addLast(new MsgpackEncoder());
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            //绑定端口，同步等待成功!
            ChannelFuture f = b.bind(port).sync();

            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {

            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        int port = 8080;

        if (args != null && args.length > 0 ){
            port = Integer.valueOf(args[0]);
        }
       new EchoServer().bind(port);
    }
}
