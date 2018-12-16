package com.github.aidan.adsl.server.service.impl;



import ch.ethz.ssh2.Session;
import com.github.aidan.adsl.server.bean.ADSL;
import com.github.aidan.adsl.server.service.IpService;
import com.github.aidan.adsl.server.util.RemoteShellTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class IpServiceImpl implements IpService {

    @Autowired
    private ADSL adsl;

    @Override
    public String refreshIp() throws IOException {
        long startTime = System.currentTimeMillis();
        RemoteShellTool tool = new RemoteShellTool(adsl.getHost(),adsl.getPort(), adsl.getUser(),
                adsl.getPwd(), "utf-8");
       String result = tool.exec("/usr/sbin/pppoe-stop && /usr/sbin/pppoe-start");
     //  tool.exec("/usr/sbin/pppoe-start");
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        System.out.print(result);
        return "success";
    }

    @Override
    public String getIp() throws IOException {
        long startTime = System.currentTimeMillis();
        RemoteShellTool tool = new RemoteShellTool(adsl.getHost(),adsl.getPort(), adsl.getUser(),
                adsl.getPwd(), "utf-8");
        String result1 ="123456";
        result1 = tool.exec("ip a | grep ppp0 |grep inet | awk '{print $2}'").trim();
        if (StringUtils.isEmpty(result1) || "123456".equals(result1)||"\n".equals(result1)||"\r".equals(result1)){
            tool.exec("/usr/sbin/pppoe-start");
            System.out.println("重新获取中");
            return tool.exec("ip a | grep ppp0 |grep inet | awk '{print $2}'").trim();
        }else {
         //   result1 =  tool.exec("ip a | grep ppp0 |grep inet | awk '{print $2}'").trim();
            log.info("IP:  "+result1+"  获取成功");
            System.out.println(result1+"1111111");
            return result1.trim();
        }
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
