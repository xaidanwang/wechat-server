package com.github.aidan.netty.java.decoder.protobuf;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang yi fei
 * @date 2019/2/19 15:12
 */
public class SubReqSlientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i =0 ; i< 10 ;i++){
            ctx.writeAndFlush(subReq(i));
        }
    }

    private SubScribeReqProto.SubscribeReq subReq(int i){
        SubScribeReqProto.SubscribeReq.Builder builder = SubScribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(i);
        builder.setUserName("Lilinfeng");
        builder.setProductName("Netty Book For Protobuf");
        List<String> address = new ArrayList<>();
        address.add("NanJing yuhuatai");
        address.add("ShenZhen hongshulin");
        address.add("beijing liulichang");
        builder.addAllAddress(address);
        return builder.build();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receiver server response :[" +msg +"]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
