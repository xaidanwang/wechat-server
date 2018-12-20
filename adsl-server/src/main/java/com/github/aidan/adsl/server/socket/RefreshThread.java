package com.github.aidan.adsl.server.socket;

import com.github.aidan.adsl.server.util.RemoteShellTool2;
import java.io.IOException;
import java.util.concurrent.*;

public class RefreshThread implements Callable<String> {

    private RemoteShellTool2 tool;

    public RefreshThread(RemoteShellTool2 tool) {
        this.tool = tool;
    }

    @Override
    public String call() {

        String result ="";
        try {
            Thread.sleep(5000);
            tool.exec1("/usr/sbin/pppoe-stop");
            Thread.sleep(10000);
            tool.exec1("/usr/sbin/pppoe-start");
        } catch (InterruptedException e) {
            e.printStackTrace();
            result ="SSH断开重新链接socket";
        } catch (IOException e) {
            e.printStackTrace();
            result ="SSH断开重新链接socket";
        }
        return result;
    }

}
