package com.github.aidan.adsl.server.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.github.aidan.adsl.server.bean.ADSL;
import com.github.aidan.adsl.server.util.RemoteShellTool2;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MessageHandler2 implements Runnable{

    public Socket socket;
    private RemoteShellTool2 tool;
    private boolean login =false;
    public MessageHandler2(Socket socket) {
        this.socket = socket;
    }
    @Autowired
    private ADSL adsl;

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(),"utf-8"));
            out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream(),"utf-8"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String result = "";
            String body = null;
                while (true){
                    body = in.readLine();
                    System.out.println(sdf.format(new Date())+"   :" +body);

                    switch (body){
                        case "1" :result = refreshIp(); break;
                        case "2" :result = getIp(); break;
                        case "3" :result = getPppoeIp(); break;
                        case "4" :result = retryGetIp(); break;
                        case "5" :result = reboot(); break;
                        default:
                         if (!login){
                             result = login(body);
                        }else {
                         result = "输入错误，请输入1 2 3 4 5";
                        }
                        break;
                    }
                    out.println(result);
                    System.out.println(sdf.format(new Date())+"   :" +result);
                    out.flush();
                }
        } catch (ConnectException e) {
            e.printStackTrace();
            out.println("SSH断开重新链接socket");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            out.println("SSH断开重新链接socket");
            out.flush();
        } finally {
            out.println("SSH断开重新链接socket");
            out.flush();
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
        String result ="";
        if (isLogin()) {
            return "请登录格式：" + JSON.toJSONString(new ADSL("127.0.0.1",8080,"q123456","root"));
        }
        RefreshBatch refreshBatch = new RefreshBatch();
        result = refreshBatch.execute(tool);
        System.out.println(result);

        return result;

    }

    private boolean isLogin() {
        if (!login){
            return true;
        }
        return false;
    }

    //2
    public String getIp(){
        String result ="";
        if (isLogin()) {
            return "请登录格式：" + JSON.toJSONString(new ADSL("127.0.0.1",8080,"q123456","root"));
        }
        try {
            result = tool.exec1("wget -qO- -t1 -T2 ipinfo.io/ip").trim();
            if (StringUtils.isNullOrEmpty(result)){
                result = "3秒后输入3查看网卡ip是否存在";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result ="连接异常重新连接socket,输入1重新开始";
        }
        System.out.println(result);
        return result;
    }
    // 3
    public String getPppoeIp(){
        String result ="";
        if (isLogin()) {
            return "请登录格式：" + JSON.toJSONString(new ADSL("127.0.0.1",8080,"q123456","root"));
        }
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
        System.out.println(result);
        return  result;
    }

    //4
    public String retryGetIp(){
        String result ="";
        if (isLogin()) {
            return "请登录格式：" + JSON.toJSONString(new ADSL("127.0.0.1",8080,"q123456","root"));
        }
        try {
            result = tool.exec1("wget -qO- -t1 -T2 ipinfo.io/ip").trim();
            if (StringUtils.isNullOrEmpty(result)){
                result = "二次获取失败1分钟后输入1重新刷新";
            }
        } catch (IOException e) {
            e.printStackTrace();
            result ="连接异常重新连接socket,输入1重新开始";
        }
        System.out.println(result);
        return result;
    }

    //5
    public String reboot(){
        String result ="";
        if (isLogin()) {
            return "请登录格式：" + JSON.toJSONString(new ADSL("127.0.0.1",8080,"q123456","root"));
        }
        try {
            result = tool.exec1("reboot").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return "重启中4分钟后连接socket";
    }


    public String authenticationFormat(String body){
        String result ="格式正确";
        try {
            adsl = JSON.parseObject(body,ADSL.class);
        }catch (JSONException e){
            e.printStackTrace();
            result = "格式错误,正确格式为："+JSON.toJSONString(new ADSL("127.0.0.1",8080,"q123456","root"));
        }
        return result;
    }

    public String login(String body){
       String result = authenticationFormat(body);
       if ("格式正确".equals(result)){
           tool = new RemoteShellTool2(adsl.getHost(), adsl.getPort(), adsl.getUsername(),
                   adsl.getPwd(), "utf-8");
           try {
               login=tool.login();
               result = "登陆成功";
           } catch (IOException e) {
               e.printStackTrace();
               result ="登陆失败,登陆信息不匹配";
           }
       }
       return result;
    }

}
