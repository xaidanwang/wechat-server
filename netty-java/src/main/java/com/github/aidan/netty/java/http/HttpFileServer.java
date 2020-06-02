package com.github.aidan.netty.java.http;

import com.github.aidan.netty.java.http.handler.HttpHandler;
import com.github.aidan.netty.java.netty.codec.NettyMessageDecoder;
import com.github.aidan.netty.java.netty.codec.NettyMessageEncoder;
import com.github.aidan.netty.java.netty.pojo.NettyConstant;
import com.github.aidan.netty.java.netty.server.HeartBeatRespHandler;
import com.github.aidan.netty.java.netty.server.LoginAuthRespHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author wang yi fei
 * @date 2019/8/22 12:08
 */
public class HttpFileServer {

	private ChannelFuture future;

	public void bind(int port) throws InterruptedException {

		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 1024)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						pipeline.addLast("http-secoder",new HttpRequestDecoder());
						pipeline.addLast("http-aggregator",new HttpObjectAggregator(65536));
						pipeline.addLast("http-encoder",new HttpResponseEncoder());
						pipeline.addLast("http-chunked",new ChunkedWriteHandler());
						pipeline.addLast("fileServerHandler",new HttpHandler());
					}
				});
		//绑定端口，同步等待成功!
		ChannelFuture f = b.bind(port).sync();
		this.future = f;
		System.out.println("Netty server start ok :" + (NettyConstant.REMOTEIP + ":" + NettyConstant.PORT));
		f.channel().closeFuture().sync();

	}

	public static void main(String[] args) throws InterruptedException {
		HttpFileServer httpFileServer = new HttpFileServer();
		System.out.println("==================");
		httpFileServer.bind(9005);
/*		new Thread(() -> {
			try {
				httpFileServer.bind(9005);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/
		Thread.sleep(5000);
		httpFileServer.closeServer();
	}

	public void closeServer(){
		try {
			this.future.channel().close().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
