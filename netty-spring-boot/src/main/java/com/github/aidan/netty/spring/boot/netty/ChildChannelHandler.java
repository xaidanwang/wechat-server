package com.github.aidan.netty.spring.boot.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wang yi fei
 * @date 2019/3/6 12:47
 */
@Component
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Resource
    private DiscardServerHandler discardServerHandler;

    public void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(discardServerHandler);
    }
}
