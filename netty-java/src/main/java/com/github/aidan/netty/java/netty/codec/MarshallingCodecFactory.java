package com.github.aidan.netty.java.netty.codec;

import org.jboss.marshalling.*;

import java.io.IOException;

/**
 * @author wang yi fei
 * @date 2019/2/25 9:15
 */
public class MarshallingCodecFactory {

    protected static Marshaller buildMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling
                .getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        Marshaller marshaller = marshallerFactory
                .createMarshaller(configuration);
        return marshaller;
    }

    protected static Unmarshaller buildUnmarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final  MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        Unmarshaller unmarshaller = marshallerFactory.createUnmarshaller(configuration);
        return unmarshaller;
    }
}
