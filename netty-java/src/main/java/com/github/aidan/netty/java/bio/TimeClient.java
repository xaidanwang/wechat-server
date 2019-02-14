package com.github.aidan.netty.java.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {

    public static void main(String[] args) {
        int port = 9000;
        if (args != null &&  args.length>0){
            try {
                port =  Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            //创建sokcet client 去连接指定的ip 与端口的服务端TimeServer
           // socket = new Socket("39.104.189.153",9024);
            socket = new Socket("127.0.0.1",8080);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            while (true){
                //循环监听
                InputStreamReader is = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(is);
                //阻塞式函数
                String name = br.readLine();
                System.out.println("ReadTest Output:" + name);
             //   out.println(name);
                socket.getOutputStream().write(name.getBytes());

                byte[] by = new byte[1024];
                socket.getInputStream().read(by);
                String re = new String(by,"UTF-8");
                System.out.println("re :"+re);

                //System.out.println("socket服务器返回信息:"+in.readLine());
            }
        }catch (Exception e){

            e.printStackTrace();
        }finally {

            if (out!=null){
                out.close();
                out=null;
            }
            if (in !=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in =null;
            }

            if (socket!=null){

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }

        }
    }
}
