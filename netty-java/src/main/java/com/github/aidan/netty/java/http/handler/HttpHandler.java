package com.github.aidan.netty.java.http.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.Format;
import java.util.zip.ZipOutputStream;

/**
 * @author wang yi fei
 * @date 2019/8/22 12:12
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


	@Override
	protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

		System.out.println(request.uri());
		System.out.println(request.headers());
		System.out.println(request.method());
		System.out.println(request.uri());
		ByteBuf bodybuf = request.content();
		int a = bodybuf.readableBytes();
		System.out.println(a);
		byte[] bodyByte = new byte[a];
		bodybuf.readBytes(bodyByte);
		int b = bodyByte.length;
		System.out.println(b);
		ByteArrayResource byteArrayResource = new ByteArrayResource(bodyByte);



		File file = new File("D:\\usr\\test.zip");
		ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
		zipOutputStream.write(bodyByte,0,a);
	}
}
