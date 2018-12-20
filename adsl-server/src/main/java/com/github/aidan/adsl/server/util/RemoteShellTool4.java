package com.github.aidan.adsl.server.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.mysql.cj.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RemoteShellTool4 {

    private Connection conn;
    private String ipAddr;
    private String charset = Charset.defaultCharset().toString();
    private String userName;
    private String password;
    private int port;

    public RemoteShellTool4(String ipAddr, int port, String userName, String password,
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
   public static void main(String[] args)  {
       RemoteShellTool4 tool = new RemoteShellTool4("157.52.202.20", 20260, "root",
               "q123456", "utf-8");
           System.out.println("连接成功");
       InputStream in = null;
       String result = "";
       FileWriter fw =null;
       try {
           fw = new FileWriter(new File("E:\\新建文件夹\\log\\log8011.txt"));
           if (tool.login()) {
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               int i =0;
               while (true) {
                   Long startTime=System.currentTimeMillis();
                   fw.write("开始第"+i+" 次获取 IP\t\n");
                   Thread.sleep(5000);
                   System.out.println("关闭 pppoe");
                   fw.write("关闭 pppoe\t\n");
                   result = tool.exec1("/usr/sbin/pppoe-stop");
                   Thread.sleep(10000);
                   fw.write("开启 pppoe\t\n");
                   result = tool.exec1("/usr/sbin/pppoe-start");

                   Thread.sleep(5000);
                   fw.write("获取ip：\t\n");
                   result = tool.exec1("wget -qO- -t1 -T2 ipinfo.io/ip").trim();

                   if (StringUtils.isNullOrEmpty(result)){
                       fw.write("wget 一次获取ip失败,2秒后查看网卡IP :\t\n");
                       Thread.sleep(2000);
                       result = tool.exec1("ip a | grep ppp* |grep inet | awk '{print $2}'").trim();
                       if (StringUtils.isNullOrEmpty(result)){
                           result = "重新拨号\t\n";
                           fw.write(result);
                           fw.flush();
                           continue;
                       }else {
                           result = "看网卡IP为："+result+"2 秒 后 wget 开始重新获取ip\t\n";
                           Thread.sleep(2000);
                           fw.write(result);
                           result = tool.exec1("wget -qO- -t1 -T2 ipinfo.io/ip").trim();
                           if (StringUtils.isNullOrEmpty(result)){
                               result = "wget 二次获取ip 失败 重新拨号 休眠1分钟\t\n";
                               System.out.println(result);
                               fw.write(result);
                               Thread.sleep(60000);
                               continue;
                           }else {
                               StringBuilder sb = new StringBuilder(sdf.format(new Date())+"wget开始第"+i+"次获取ip二次成功,ip 为:");
                               result =  sb.append(result).toString();
                               fw.write(result+"\t\n");
                           }
                       }
                   }else {
                       StringBuilder sb = new StringBuilder(sdf.format(new Date())+"wget开始第"+i+"次获取ip一次成功,ip 为:");
                       result =  sb.append(result).toString();
                       fw.write(result+"\t\n");
                   }
                   System.out.println("result :"+result);
                   System.out.println("获取ip 总耗时 ："+(System.currentTimeMillis()-startTime)/1000+"秒");
                   fw.write("获取ip 总耗时 ："+(System.currentTimeMillis()-startTime)/1000+"秒");
                   fw.flush();
                   i++;
               }
           }

       }catch (Exception e){
           e.printStackTrace();
       }finally {
           try {
               fw.flush();
               tool.conn.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
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


    public Connection getConn() {
        return conn;
    }

    public String getCharset() {
        return charset;
    }

    public String exec1(String cmds) {
        InputStream in = null;
        String result = "";
        try {
            Session session = this.conn.openSession();
            session.execCommand(cmds);
            in = session.getStdout();
            result = this.processStdout(in, this.charset);
            session.close();
            } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
   }
}
