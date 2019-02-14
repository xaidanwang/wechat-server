package com.github.aidan.netty.java.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//具体的socket 交互处理信息的实体
public class TimeServerHandler implements Runnable {
    private Socket socket;
    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream());
            String currentTime = null;
            String body = null;
            while (true){
                /**
                 *BufferedReader的readLine()方法是阻塞式的, 如果到达流末尾,
                 * 就返回null,但如果client的socket末经关闭就销毁, 则会产生IO异常.
                 * 正常的方法就是使用socket.close()关闭不需要的socket
                 */
                body = in.readLine();
                if (body == null){
                    break;
                }
                System.out.println("The time server recevice order :" + body);
                currentTime = "QUERY TIME ORDER ".equalsIgnoreCase(body)?new java.util.Date((System.currentTimeMillis())).toString(): "BAD ORDER";
                out.println(currentTime);
            }
        } catch (Exception e) {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null){
                out.close();
                out = null;
            }
            if (this.socket != null){
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.socket =null;
            }
        }

    }
}
