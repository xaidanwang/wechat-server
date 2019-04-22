package com.github.aidan.netty.java.netty3.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang yi fei
 * @date 2019/2/28 16:42
 */
@Data
@ToString
public class Header {
    private int crcCode = 0xabef0101;
    //消息长度
    private int length;
    //回话ID
    private long sessionID;
    //消息类型
    private byte type;
    private byte priority;
    private Map<String,Object> attachment = new HashMap<String, Object>();

//    private int crcCode = 0xabef0101;
//    private int length;
//    //消息长度
//    private long sessionID;
//    private byte type;//消息类型
//    private byte priority;//消息优先级
//    private Map<String, Object> attachment = new HashMap<String, Object>();//附件
}
