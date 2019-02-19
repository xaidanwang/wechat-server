package com.github.aidan.netty.java.decoder.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang yi fei
 * @date 2019/2/19 10:20
 */
public class TestSubscribeReqProto {

    private static byte[] encode (SubScribeReqProto.SubscribeReq req){
        return  req.toByteArray();
    }

    private static SubScribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {

        return SubScribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubScribeReqProto.SubscribeReq createSubcribeReq(){

        SubScribeReqProto.SubscribeReq.Builder builder = SubScribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(1);
        builder.setUserName("wang yi fei ");
        builder.setProductName("Netty Book");
        List<String> address = new ArrayList<>();
        address.add("NanJing yuhuatai");
        address.add("ShenZhen hongshulin");
        address.add("beijing liulichang");
        builder.addAllAddress(address);
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubScribeReqProto.SubscribeReq req = createSubcribeReq();
        System.out.println("Before encode : "+ req.toString());
        SubScribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("After decode : " + req.toString());
        System.out.println("Assert equal : ---> " + req2.equals(req));
    }
}
