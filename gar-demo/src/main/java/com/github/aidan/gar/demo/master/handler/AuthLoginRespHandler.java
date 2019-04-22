package com.github.aidan.gar.demo.master.handler;

import com.github.aidan.gar.demo.pojo.ComRequest;
import com.github.aidan.gar.demo.pojo.ComResponse;
import com.github.aidan.gar.demo.pojo.auth.User;
import com.github.aidan.gar.demo.service.LoginAuth;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wang yi fei
 * @date 2019/3/1 15:40
 */
@Slf4j
public class AuthLoginRespHandler extends ChannelHandlerAdapter{

    private static Map<String,Boolean> clientMap = new ConcurrentHashMap<>();

    @Autowired
    private LoginAuth loginAuth;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ComRequest request = (ComRequest)msg;
        log.info("接收客户端登陆信息:[{}]",msg);

        //判断是否是登陆信息,是走登陆验证流程,不是交给下一个（透传）handler处理
        if ("login".equalsIgnoreCase(request.getTopic())){

            String transactionId = request.getTransactionId();
            ComResponse loginResp = null;
            //验证是否重复登陆
            if (clientMap.containsKey(transactionId)){
                log.info("重复登陆,登陆信息:[{}]",msg);
                //重复登陆
                loginResp =null;
            }else {
                //记录已经登陆的客户端
                // 验证客户端
                User user = (User)request.getData();
                if (loginAuth.accountVerification(user.getUsername(),user.getPassword())){
                    clientMap.put(transactionId,true);
                }
                //返回登陆回应
                loginResp = new ComResponse();
                loginResp.setResult(0);
            }
            log.info("服务端返回登陆回应信息 loginResp:[{}]",loginResp);
            ctx.writeAndFlush(loginResp);

        }else if ("logout".equalsIgnoreCase(request.getTopic())){
            clientMap.remove("1111111111111111");
            ctx.close();
        }else {
            ctx.fireChannelRead(msg);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //删除缓存
        clientMap.remove("1111111111111111");
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }
}
