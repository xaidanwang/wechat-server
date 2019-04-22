package com.github.aidan.netty.java.netty3.codec;

import com.github.aidan.netty.java.netty3.codec.NettyMarshallingEncoder;
import com.github.aidan.netty.java.netty3.pojo.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;
import java.util.Map;

/**
 * @author wang yi fei
 * @date 2019/2/28 16:41
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

   private NettyMarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() {
        this.marshallingEncoder = MarshallingCodecFactory.buildMarshallEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage nettyMessage, List<Object> list) throws Exception {

        if (nettyMessage == null || nettyMessage.getHeader() == null) {
            throw new Exception("the encode message is null");
        }

        ByteBuf sendBuf = Unpooled.buffer();
        sendBuf.writeInt(nettyMessage.getHeader().getCrcCode());
        sendBuf.writeInt(nettyMessage.getHeader().getLength());
        sendBuf.writeLong(nettyMessage.getHeader().getSessionID());
        sendBuf.writeByte(nettyMessage.getHeader().getType());
        sendBuf.writeByte(nettyMessage.getHeader().getPriority());
        //将扩展头的信息个数写入缓存
        sendBuf.writeInt(nettyMessage.getHeader().getAttachment().size());

        String key = null;
        byte[] keyArray = null;
        Object value = null;

        //编码码扩展的信息头
        for (Map.Entry<String, Object> param : nettyMessage.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            //写入 key 所占的字节数
            sendBuf.writeInt(keyArray.length);
            //key 进行编码
            sendBuf.writeBytes(keyArray);
            value = param.getValue();
            //使用 marshallingEncoder 对对象进行编码,实际上调用MarshallingEncoder.encode 方法
            //改类继承MessageToByteEncoder，
            //编码规则为 ByetBuf 中 4个字节写入 Object 经过编码后的字节长度，然后在写入Object编码后的字节
            marshallingEncoder.encode(ctx,value,sendBuf);
        }
        key = null;
        keyArray = null;
        value = null;

        //判断信息体中是否有信息
        if (nettyMessage.getBody() != null) {
            //有使用marshallingEncoder编码方式继续编码
            marshallingEncoder.encode(ctx, nettyMessage.getBody(), sendBuf);
        } else {
            //没有写入0
            sendBuf.writeInt(0);
        }

        // 在第4个字节出写入Buffer的长度
        int readableBytes = sendBuf.readableBytes();
        sendBuf.setInt(4, readableBytes);
        // 把Message添加到List传递到下一个Handler
        list.add(sendBuf);
    }


}
