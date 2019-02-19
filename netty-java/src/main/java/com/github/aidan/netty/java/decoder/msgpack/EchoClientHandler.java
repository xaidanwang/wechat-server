package com.github.aidan.netty.java.decoder.msgpack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang yi fei
 * @date 2019/2/18 10:48
 */
public class EchoClientHandler extends ChannelHandlerAdapter {

    private final int sendNumber;

    private int count;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        List<UserInfo> infos = userInfo();

        System.out.println("发送人员信息 ："+infos.toString());
        for (UserInfo userInfo: infos){
            System.out.println("发送信息 ："+userInfo.toString());
            ctx.writeAndFlush(userInfo);
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("这是客户端接收的消息【  " + ++count + "  】时间:【" + msg + "】");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private List<UserInfo> userInfo(){
        List<UserInfo> userInfos = new ArrayList<>();
        UserInfo userInfo = null;
        for (int i = 0;i < sendNumber ; i++){
            userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setName("ABCDEFG ------->" + i);
            userInfos.add(userInfo);
        }

        return userInfos;
    }
}
