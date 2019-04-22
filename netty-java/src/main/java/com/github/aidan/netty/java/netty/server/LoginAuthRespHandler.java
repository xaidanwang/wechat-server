package com.github.aidan.netty.java.netty.server;

import com.github.aidan.netty.java.netty.pojo.Header;
import com.github.aidan.netty.java.netty.pojo.MessageType;
import com.github.aidan.netty.java.netty.pojo.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wang yi fei
 * @date 2019/2/25 16:04
 */
@Slf4j
public class LoginAuthRespHandler extends ChannelHandlerAdapter {
    private Map<String,Boolean> nodeCheck =  new ConcurrentHashMap<>();
    private String[] whiteList = {"127.0.0.1","192.168.1.104"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message=(NettyMessage)msg;
        //如果是握手请求，处理，其他消息透传
        log.info("处理握手请求");
        if (message.getHeader()!=null && message.getHeader().getType() == MessageType.LOGIN_REQ.value()){
            String nodeIndex = ctx.channel().remoteAddress().toString();
            log.info("nodeIndex.toString :"+nodeIndex);
            NettyMessage loginResp = null;
            //重复登陆拒绝
            if (nodeCheck.containsKey(nodeIndex)){
                loginResp = buildResponse((byte)-1);
            }else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                log.info("登陆ip 地址为 ip：[{}]",ip);
                boolean isOk = false;
                for (String wip:whiteList){
                    if (wip.equals(ip)){
                        isOk = true;
                        break;
                    }
                }
                loginResp = isOk?buildResponse((byte)0):buildResponse((byte)-1);
                if (isOk){
                    nodeCheck.put(nodeIndex,true);
                }
            }
            System.out.println("The login response is ："+loginResp+" body ["+loginResp.getBody()+"]");
            ctx.writeAndFlush(loginResp);
        }else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildResponse(byte result){
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType((byte)4);
        message.setHeader(header);
        message.setBody(result);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //删除缓存
        nodeCheck.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }


}
