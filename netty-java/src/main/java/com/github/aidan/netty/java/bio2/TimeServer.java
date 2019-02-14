package com.github.aidan.netty.java.bio2;

import com.github.aidan.netty.java.bio.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wang yi fei
 * @date 2019/2/13 10:53
 */
public class TimeServer {

    public static void main(String[] args) {
        //dsocket 监听的端口
        int port = 8080;
        if (args != null &&  args.length>0){
            try {
                port =  Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
            }
        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Socket socket =null;
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,1000);
            while (true){
                socket = serverSocket.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
