package com.github.aidan.adsl.server.service.impl;

import ch.ethz.ssh2.Session;

import com.github.aidan.adsl.server.service.IpService;
import com.github.aidan.adsl.server.util.RemoteShellTool;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class IpServiceImpl implements IpService {


    @Override
    public String refreshIp() throws IOException {
        long startTime = System.currentTimeMillis();
        RemoteShellTool tool = new RemoteShellTool("172.247.116.62",20279, "root",
                "225286", "utf-8");
        String result = tool.exec("pppoe-stop && pppoe-start");
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        System.out.print(result);
        return "success";
    }

    @Override
    public String getIp() throws IOException {
        long startTime = System.currentTimeMillis();
        RemoteShellTool tool = new RemoteShellTool("172.247.116.62",20279, "root",
                "225286", "utf-8");
        String result1 = tool.exec("ip a | grep ppp0 |grep inet | awk '{print $2}'");
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        System.out.print(result1);
        return result1;
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

    public String exec(String cmds,Session session) {
        InputStream in = null;
        String result = "";
        try {
                session.execCommand(cmds);
                in = session.getStdout();
                result = this.processStdout(in, "utf-8");
                session.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return result;
    }
}
