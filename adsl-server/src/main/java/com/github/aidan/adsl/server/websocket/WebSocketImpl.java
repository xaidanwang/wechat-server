package com.github.aidan.adsl.server.websocket;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description
 * @Author ArchSun
 * @create: 2018-09-17 19:49
 **/
@ServerEndpoint(value = "/socket-bus")
@Component
@Slf4j
public class WebSocketImpl {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    // app 离线通知
    private static final  String QUEUE_APP_OFF_LINE_MESSAGE = "TOPIC_APP_OFF_LINE";

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketImpl对象。
    private static CopyOnWriteArraySet<WebSocketImpl> webSocketSet = new CopyOnWriteArraySet<WebSocketImpl>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String useruuid;
    private static List<Session> sessions = new ArrayList<Session>();
    private static List<String> names = new ArrayList<String>();
    private static Map<String, Session> map = new HashMap<String, Session>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        String queryString = session.getQueryString();
        log.info("queryString              "+queryString);
        if (queryString != null && queryString.contains("=")) {
            //this.useruuid = queryString.split("=")[1];
            this.useruuid = queryString.substring(9);
            log.info("useruuid              "+this.useruuid );
            if (!"".equals(this.useruuid)){
                map.put(this.useruuid, session);
            }
        }
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("发送新消息");
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {

        if (!"".equals(this.useruuid)){
            map.remove(this.useruuid);    //从map中删除
        }
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        synchronized (this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
/*        for (WebSocketImpl item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        synchronized (this.session) {
            this.session.getBasicRemote().sendText(message);
        }
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        for (WebSocketImpl item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketImpl.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketImpl.onlineCount--;
    }
}
