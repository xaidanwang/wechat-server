package com.github.aidan.adsl.server.socket;

import com.github.aidan.adsl.server.util.RemoteShellTool2;
import com.mysql.cj.util.StringUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;


public class MessageHandler implements Runnable{

    public Socket socket;

    private static RemoteShellTool2 tool = new RemoteShellTool2("157.52.202.20", 20460, "root",
            "q123456", "utf-8");

    public MessageHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream());
            String result = "";
            String body = null;
            if (tool.login()){
                while (true){
                    body = in.readLine();
                    if (body == null){
                        break;
                    }
                    switch (body){
                        case "1" :result = refreshIp(); break;
                        case "2" :result = getIp(); break;
                        case "3" :result = getPppoeIp(); break;
                        case "4" :result = retryGetIp(); break;
                        case "5" :result = reboot(); break;
                        default: result ="输入错误，请输入1 2 3 4 5";break;
                    }
                    out.println(result);
                    out.flush();
                }
            }
        } catch (ConnectException e) {
            e.printStackTrace();
            out.println("SSH无法连接接检查机器");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Util.close(in);
            Util.close(out);
            Util.close(socket);
        }
    }

    /**
     * 刷新Ip
     * @return
     */
    //1
    public String refreshIp(){
        RefreshBatch refreshBatch = new RefreshBatch();
        return refreshBatch.execute(tool);
    }
//2
    public String getIp(){
        String result ="";
        try {
            result = tool.exec1("wget -qO- -t1 -T2 ipinfo.io/ip").trim();
            if (StringUtils.isNullOrEmpty(result)){
                result = "3秒后输入3查看网卡ip是否存在";
            }
        } catch (IOException e) {
            e.printStackTrace();
            result ="连接异常重新连接socket,输入1重新开始";
        }
        return result;
    }
    // 3
    public String getPppoeIp(){
        String result ="";
        try {
            result = tool.exec1("ip a | grep ppp* |grep inet | awk '{print $2}'").trim();
            if (StringUtils.isNullOrEmpty(result)){
                result = "网卡ip不存在输入一分钟后输入1重新刷新";
            }else {
                result = "网卡ip存在,2秒后输入4尝试二次获取";
            }
        } catch (IOException e) {
            e.printStackTrace();
            result ="连接异常重新连接socket,输入1重新开始";
        }
        return  result;
    }

    //4
    public String retryGetIp(){
        String result ="";
        try {
            result = tool.exec1("wget -qO- -t1 -T2 ipinfo.io/ip").trim();
            if (StringUtils.isNullOrEmpty(result)){
                result = "二次获取失败1分钟后输入1重新刷新";
            }
        } catch (IOException e) {
            e.printStackTrace();
            result ="连接异常重新连接socket,输入1重新开始";
        }
        return result;
    }

    //5
    public String reboot(){
        String result ="";
        try {
            result = tool.exec1("reboot").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "重启中4分钟后连接socket";
    }

}
