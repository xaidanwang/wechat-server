package com.github.aidan.netty.java.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//时间服务器给予BIO sockert 阻塞式的
public class TimeServer {

    public static void main(String[] args) throws IOException {
        //dsocket 监听的端口
        int port = 8080;
        if (args != null &&  args.length>0){
            try {
                port =  Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("Time server is start in port :" + port);
            Socket socket = null;
            while (true){
                //一个阻塞式的函数，当有socket client 连接过来时才会执行  new Thread(new TimeServerHandler(socket)).start();
                socket = server.accept();
                //每一个socket 连接过来之后都要创建的线程处理这个连接，当成千上万的客户同时使用资源大量浪费
                //不利于高并发，高性能的要求
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (server != null){
                System.out.println("Time server close");
                server.close();
                server = null;
            }
        }
    }

}
