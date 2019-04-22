package com.github.aidan.netty.java.netty.pojo;

import java.io.Serializable;

/**
 * @author wang yi fei
 * @date 2019/2/25 11:47
 */
public enum MessageType {

    LOGIN_REQ((byte) 3),
    LOGIN_RESP((byte) 4),
    HEARTBEAT_REQ((byte) 5),
    HEARTBEAT_RESP((byte) 6);

    private byte value;

    private MessageType(byte value) {
        this.value = value;
    }

    public byte value() {
        return this.value;
    }
}
