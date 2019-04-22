package com.github.aidan.netty.java.netty3;

/**
 * @author wang yi fei
 * @date 2019/2/28 15:44
 */
public enum NettyType {

    //登陆验证
    LOGIN_AUTH_REQ((byte)3),
    //登陆回应
    LOGIN_AUTH_RESP((byte)4),
    //心跳发送
    BEAT_HEART_RRP((byte)5),
    //心跳回应
    BEAT_HEART_RESP((byte)6);

    public byte value;

    NettyType(byte value) {
        this.value = value;
    }
}
