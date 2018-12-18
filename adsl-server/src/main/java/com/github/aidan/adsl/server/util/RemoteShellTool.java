package com.github.aidan.adsl.server.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.mysql.cj.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
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

       RemoteShellTool tool = new RemoteShellTool("157.52.202.19", 20302, "root",
               "q123456", "utf-8");
           System.out.println("连接成功");
           Connection connection = new Connection("157.52.202.19",20302);
           boolean flag = connection.authenticateWithPassword("root","q123456");
           if (flag) {
               while (true) {
                   Session session = connection.openSession();
                   InputStream is = System.in;
                   InputStreamReader isr = new InputStreamReader(is);
                   BufferedReader br = new BufferedReader(isr);
                   try {
                       System.out.println(br.readLine());
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   session.execCommand(br.readLine());
                   System.out.println(tool.processStdout(session.getStderr(), "utf-8"));
                   System.out.println(tool.processStdout(session.getStdout(), "utf-8"));
                   session.close();
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
}
