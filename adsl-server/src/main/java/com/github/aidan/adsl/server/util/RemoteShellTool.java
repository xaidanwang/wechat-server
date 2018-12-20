package com.github.aidan.adsl.server.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import java.io.*;
import java.nio.charset.Charset;

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
 /*   public static void main(String[] args) throws IOException {
        RemoteShellTool tool = new RemoteShellTool("157.52.202.19",20302, "root",
                "q123456", "utf-8");

        if (tool.login()){

            while (true){
                InputStreamReader is = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(is);
                try{ //该方法中有个IOExcepiton需要捕获
                    Session session = tool.conn.openSession();
                    String name = br.readLine();
                    System.out.println("ReadTest Output:" + name);
                    InputStream in = session.getStdout();
                    session.execCommand(name);
                    String result = tool.processStdout(in, tool.charset);
                    System.out.println(result);
                    session.close();
                }finally {

                }
            }
        }

    }*/


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
