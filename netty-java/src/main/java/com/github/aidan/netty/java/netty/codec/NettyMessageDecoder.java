package com.github.aidan.netty.java.netty.codec;

import com.github.aidan.netty.java.netty.pojo.Header;
import com.github.aidan.netty.java.netty.pojo.NettyMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang yi fei
 * @date 2019/2/25 10:43
 */
@Slf4j
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    NettyMarshallingDecoder marshallingDecoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
        this.marshallingDecoder = MarshallingCodecFactory.buildMarshallingDecoder();
    }

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        this.marshallingDecoder = MarshallingCodecFactory.buildMarshallingDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        log.info("NettyMessageDecoder 开始解码");
        ByteBuf frame = (ByteBuf) super.decode(ctx,in);
        if (frame == null){
            System.out.println("NettyMessageDecoder NULL");
            return null;
        }
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionID(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());
        int size = frame.readInt();

        if (size > 0){
            Map<String,Object> attch = new HashMap<>(size);
            int keySize = 0;
            byte[] keyArray = null;
            String key = null;
            for (int i=0;i <size;i++){
                keySize = frame.readInt();
                keyArray = new byte[keySize];
                in.readBytes(keyArray);
                key = new String(keyArray, "UTF-8");
                attch.put(key, marshallingDecoder.decode(ctx, frame));
            }
            keyArray = null;
            key = null;
            header.setAttachment(attch);
        }

        if (frame.readableBytes()>0){
            message.setBody(marshallingDecoder.decode(ctx,frame));
        }
        message.setHeader(header);
        return message;
    }
}
