package com.github.aidan.netty.java.decoder.protobuf;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wang yi fei
 * @date 2019/2/19 13:39
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubScribeReqProto.SubscribeReq req = (SubScribeReqProto.SubscribeReq)msg;

        if ("Lilinfeng".equalsIgnoreCase(req.getUserName())){
            System.out.println("Service accept client subscribe req : [" +req.toString()+"]");
            ctx.writeAndFlush(resp(req.getSubReqId()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.getStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private SubScribeRespProto.SubscribeResp resp(int subReqID){
        SubScribeRespProto.SubscribeResp.Builder builder = SubScribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqId(subReqID);
        builder.setRespCode(0);
        builder.setDesc("Netty book order succeed, 3 days later, sent to the designated address");

        return builder.build();
    }
}
