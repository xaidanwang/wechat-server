package com.github.aidan.netty.java.netty2.pojo;

/**
 * @author wang yi fei
 * @date 2019/2/26 18:06
 */
public enum MessageType {

    //心跳请求，应答
    HEARTBEAT_REQ((byte) 5),
    HEARTBEAT_RESP((byte) 6),

    //握手请求，应答
    LOGIN_REQ((byte) 3),
    LOGIN_RESP((byte) 4);

    public byte value;

    MessageType(byte value) {
        this.value = value;
    }

}
