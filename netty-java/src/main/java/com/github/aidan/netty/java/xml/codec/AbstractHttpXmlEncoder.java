package com.github.aidan.netty.java.xml.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.springframework.beans.BeanUtils;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author wang yi fei
 * @date 2019/2/22 10:24
 */
public abstract class AbstractHttpXmlEncoder<T> extends MessageToMessageEncoder<T> {

    IBindingFactory factory = null;
    StringWriter stringWriter = null;
    final static String CHARSET_NAME = "UTF-8";
    final static Charset UTF_8 = Charset.forName(CHARSET_NAME);

    protected ByteBuf encode0(ChannelHandlerContext ctx, Object body) throws Exception {
        factory = BindingDirectory.getFactory(body.getClass());
        stringWriter = new StringWriter();
        IMarshallingContext mctx = factory.createMarshallingContext();
        mctx.setIndent(2);
        mctx.marshalDocument(body, CHARSET_NAME, null, stringWriter);
        String xmlStr = stringWriter.toString();
        stringWriter.close();
        stringWriter = null;
        ByteBuf encodeBuf = Unpooled.copiedBuffer(xmlStr, UTF_8);
        return encodeBuf;
    }

}
