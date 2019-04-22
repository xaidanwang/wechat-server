package com.github.aidan.netty.java.decoder.marshalling;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * @author wang yi fei
 * @date 2019/2/20 9:46
 */
public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("开始发送信息");
        for(int i=1;i<=5;i++){
            Send send = new Send();
            send.setId(i);
            send.setMessage("message"+i);
            send.setName("name"+i);

            People people = new People();
            people.setAge(i);
            people.setName("testName"+i);
            send.setData(people);

//            Car car = new Car();
//            car.setDate(new Date());
//            car.setVicehno(String.valueOf(i));
//            send.setData(car);
            ctx.writeAndFlush(send);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Receive receive = (Receive) msg;
        System.out.println("server反馈："+receive);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
