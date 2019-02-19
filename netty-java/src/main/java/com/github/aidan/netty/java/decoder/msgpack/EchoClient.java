package com.github.aidan.netty.java.decoder.msgpack;

import com.github.aidan.netty.java.decoder.TimeClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author wang yi fei
 * @date 2019/2/16 20:09
 */
public class EchoClient {

    private String host;
    private int port;
    private int sendNumber;

    public EchoClient(String host, int port, int sendNumber) {
        this.host = host;
        this.port = port;
        this.sendNumber = sendNumber;
    }


    public void run() throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();

        try {

            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,3000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast("msgpack decoder",new MsgpackDecoder());
//                            ch.pipeline().addLast("msgpack encoder",new MsgpackEncoder());
//                            ch.pipeline().addLast(new EchoClientHandler(sendNumber));

                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,0,
                                    2,0,2));
                            ch.pipeline().addLast(new MsgpackDecoder());
                            ch.pipeline().addLast(new LengthFieldPrepender(2));
                            ch.pipeline().addLast(new MsgpackEncoder());
                            ch.pipeline().addLast(new EchoClientHandler(sendNumber));
                        }
                    });

            //发起异步连接操作
            ChannelFuture f = b.connect(host,port).sync();
            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;

        if (args != null && args.length > 0 ){
            port = Integer.valueOf(args[0]);
        }

      new EchoClient("127.0.0.1",port,10).run();
    }
}
