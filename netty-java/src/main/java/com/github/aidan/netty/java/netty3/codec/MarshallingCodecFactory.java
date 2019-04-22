package com.github.aidan.netty.java.netty3.codec;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * @author wang yi fei
 * @date 2019/2/28 15:56
 */
public class MarshallingCodecFactory {

    public static NettyMarshallingEncoder buildMarshallEncoder(){

        MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider marshallerProvider = new DefaultMarshallerProvider(factory,configuration);
        NettyMarshallingEncoder encoder = new NettyMarshallingEncoder(marshallerProvider);
        return encoder;
    }

    public static NettyMarshallingDecoder buildUnmarshallDecoder(){
        MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        UnmarshallerProvider unmarshallerProvider = new DefaultUnmarshallerProvider(factory,configuration);
        NettyMarshallingDecoder decoder = new NettyMarshallingDecoder(unmarshallerProvider);
        return decoder;
    }
}
