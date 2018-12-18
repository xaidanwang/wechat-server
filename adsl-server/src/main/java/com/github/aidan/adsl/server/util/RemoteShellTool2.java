package com.github.aidan.adsl.server.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RemoteShellTool2 {

    private Connection conn;
    private String ipAddr;
    private String charset = Charset.defaultCharset().toString();
    private String userName;
    private String password;
    private int port;

    public RemoteShellTool2(String ipAddr, int port, String userName, String password,
                            String charset) {
        this.ipAddr = ipAddr;
        this.userName = userName;
        this.password = password;
        this.port = port;
        if (charset != null) {
            this.charset = charset;
        }
    }

    public boolean login() throws IOException {
       // conn = new Connection(ipAddr);
        long startTime = System.currentTimeMillis();
        conn = new Connection(ipAddr,port);
        conn.connect(); // 连接
        long endTime = System.currentTimeMillis();
        System.out.println("创建连接时间：" + (endTime - startTime) + "ms");
        return conn.authenticateWithPassword(userName, password); // 认证


    }

    public String exec(String cmds) {
        InputStream in = null;
        String result = "";
        try {
            if (this.login()) {
                long startTime = System.currentTimeMillis();
                Session session = conn.openSession(); // 打开一个会话
                long endTime = System.currentTimeMillis();
                System.out.println("创建会话时间：" + (endTime - startTime) + "ms");

                session.execCommand(cmds);
                long endTime1 = System.currentTimeMillis();
                in = session.getStdout();
                System.out.println("执行命令时间：" + (endTime1 - endTime) + "ms");
                result = this.processStdout(in, this.charset);
                session.close();
                conn.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    public String processStdout(InputStream in, String charset) {

        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        try {
            while (in.read(buf) != -1) {
                sb.append(new String(buf, charset));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        RemoteShellTool2 tool = new RemoteShellTool2("172.247.116.62",20279, "root",
                "225286", "utf-8");
        String result="";
        FileOutputStream fos=new FileOutputStream(new File("H:\\新建文件夹\\log\\log.txt"));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
        String closeppoe="关闭拨号"+"\t\n";
        String openppoe = "开始拨号"+"\t\n";
        String ppoeNull = "拨号为空\t\n";
        String catchIp = "开始获取ip\t\n";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (tool.login()){
            int i=0;
            while (true){
                Thread.sleep(3000);
                System.out.println("关闭拨号");
                bw.write(closeppoe);
                tool.exec1(" /usr/sbin/pppoe-stop");
                Thread.sleep(5000);
                System.out.println("开始拨号");
                bw.write(openppoe);
                result = tool.exec1("/usr/sbin/pppoe-start");
                if (!StringUtils.isEmpty(result)){
                    System.out.println("拨号为空");
                    bw.write(ppoeNull);
                    return;
                }
                Thread.sleep(10000);
                System.out.println("开始获取ip");
                bw.write(catchIp);
                result = tool.exec1("wget -qO- -t1 -T2 ipinfo.io/ip").trim();
                if (StringUtils.isEmpty(result)){
                    Thread.sleep(2000);
                    System.out.println("ip 为空 查看网卡信息");
                    bw.write("获取IP 失败 查看 网卡信息 是否又IP信息"+"\t\n");
                    result = tool.exec1("ip a | grep ppp* |grep inet | awk '{print $2}'").trim();
                    bw.write(sdf.format(new Date())+"ppp* 网卡获取第 "+i+"次 ip 为"+result+"\t\n");
                    System.out.println("ppp* 网卡 ip 为"+result);
                    if (StringUtils.isEmpty(result)){
                        result = "重新拨号";
                        System.out.println(result);
                    }
                    result = tool.exec1("curl www.baidu.com ").trim();
                    if (StringUtils.isEmpty(result)){
                        result = "稍后重试";
                        System.out.println(result);
                    }else {
                        Thread.sleep(2000);
                        result = tool.exec1("wget -qO- -t1 -T2 ipinfo.io/ip").trim();
                        bw.write(sdf.format(new Date())+"ppp* 网卡获取IP成功wget 重新获取第 "+i+" 次ip为"+result+"\t\n");
                        System.out.println(result);
                    }
                }
                System.out.println(result);
                bw.write(sdf.format(new Date())+"wget 直接获取ip成功第 "+i+"次ip为"+result+"\t\n\r");
                bw.flush();
                i++;
            }
        }




/*        long startTime = System.currentTimeMillis();
        RemoteShellTool tool = new RemoteShellTool("172.247.116.221",20359, "root",
                "rv10m829", "utf-8");
        String result = tool.exec("pppoe-stop && pppoe-start");
        String result1 = tool.exec("ip a | grep ppp0 |grep inet | awk '{print $2}'");
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        System.out.print(result1);
        System.out.print(result);*/
    }
    public String exec1(String cmds) {
        InputStream in = null;
        String result = "";
        try {
            Session session = conn.openSession(); // 打开一个会话
            session.execCommand(cmds);
            in = session.getStdout();
            result = this.processStdout(in, this.charset);
            session.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return result;
    }
}
