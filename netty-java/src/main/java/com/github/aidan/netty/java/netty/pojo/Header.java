package com.github.aidan.netty.java.netty.pojo;

import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang yi fei
 * @date 2019/2/23 18:26
 */
public class Header {

    private int crcCode = 0xabef0101;
    //消息长度
    private int length;
    //回话ID
    private Long sessionID;
    //消息类型
    private byte type;
    private byte priority;
    private Map<String,Object> attachment = new HashMap<>();

    public int getCrcCode() {
        return crcCode;
    }

    public int getLength() {
        return length;
    }

    public Long getSessionID() {
        return sessionID;
    }

    public byte getType() {
        return type;
    }

    public byte getPriority() {
        return priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setSessionID(Long sessionID) {
        this.sessionID = sessionID;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionID=" + sessionID +
                ", type=" + type +
                ", priority=" + priority +
                ", attachment=" + attachment +
                '}';
    }
}
