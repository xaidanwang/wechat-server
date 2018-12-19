package com.github.aidan.adsl.server.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.mysql.cj.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RemoteShellTool {

    private Connection conn;
    private String ipAddr;
    private String charset = Charset.defaultCharset().toString();
    private String userName;
    private String password;
    private int port;

    public RemoteShellTool(String ipAddr, int port, String userName, String password,
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
        // 认证
        return conn.authenticateWithPassword(userName, password);


    }

    public String exec(String cmds) {
        InputStream in = null;
        String result = "";
        try {
            if (this.login()) {
                long startTime = System.currentTimeMillis();
                // 打开一个会话
                Session session = conn.openSession();
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
       RemoteShellTool tool = new RemoteShellTool("172.247.116.62", 20279, "root",
               "225286", "utf-8");
           System.out.println("连接成功");
       InputStream in = null;
       String result = "";
           if (tool.login()) {
               Connection connection =tool.conn;
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               int i =0;
               while (true) {
/*                   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                   String cmds=br.readLine();*/
                   Thread.sleep(5000);
                   Session session = connection.openSession();
                   System.out.println("关闭 pppoe");
                   session.execCommand("/usr/sbin/pppoe-stop ");
                   session.close();

                   Thread.sleep(10000);
                   session = connection.openSession();
                   System.out.println("打开 pppoe");
                   session.execCommand("/usr/sbin/pppoe-start ");
                   session.close();

                   Thread.sleep(10000);
                   session = connection.openSession();
                   session.execCommand("wget -qO- -t1 -T2 ipinfo.io/ip ");
                   in = session.getStdout();
                   result = tool.processStdout(in, tool.charset).trim();
                   session.close();
                   if (StringUtils.isNullOrEmpty(result)){
                       Thread.sleep(2000);
                       session = connection.openSession();
                       session.execCommand("ip a | grep ppp* |grep inet | awk '{print $2}' ");
                       in = session.getStdout();
                       result = tool.processStdout(in, tool.charset).trim();
                       session.close();
                       System.out.println(sdf.format(new Date())+"获取第 "+i+"次 ppp* ip 为"+result);
                       if (StringUtils.isNullOrEmpty(result)){
                           Thread.sleep(2000);
                           session = connection.openSession();
                           session.execCommand("wget -qO- -t1 -T2 ipinfo.io/ip ");
                           in = session.getStdout();
                           result = tool.processStdout(in, tool.charset).trim();
                           session.close();
                       }
                   }
                   System.out.println(sdf.format(new Date())+"获取第 "+i+"次 ip 为"+result);
                   i++;
               }
           }
/*       long startTime = System.currentTimeMillis();
        RemoteShellTool tool = new RemoteShellTool("172.247.116.221",20359, "root",
                "rv10m829", "utf-8");
        String result = tool.exec("pppoe-stop && pppoe-start");
        String result1 = tool.exec("ip a | grep ppp0 |grep inet | awk '{print $2}'");
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        System.out.print(result1);
        System.out.print(result);*/


 /*       //BeyondCompare路径
        String filePath = "C:\\Users\\Administrator\\AppData\\Roaming\\BeyondCompare\\BeyondCompare419.ini";
        //创建文件对象
        File BCFile = new File(filePath);
        //创建FileReader对象
        FileReader frBCFile = new FileReader(BCFile);
        //创建Buffered对象
        BufferedReader br = new BufferedReader(frBCFile);

        //读取文件内容
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if (line.indexOf("InstallTime") != -1) {
                //获取当前时间戳，因为获取到的是13位，而文件内是10位，故分割一下字符串
                String time =String.valueOf(System.currentTimeMillis()).substring(0,9);
                sb.append("InstallTime="+time+"\r\n");
            }else {
                sb.append(line+"\r\n");
            }
        }

        //写入文件
        BufferedWriter bw = new BufferedWriter(new FileWriter(BCFile));
        bw.write(sb.toString());
        System.out.println(sb.toString());
        bw.flush();

        //关闭流
        frBCFile.close();
    }*/
   }

    public Connection getConn() {
        return conn;
    }

    public String getCharset() {
        return charset;
    }


}
