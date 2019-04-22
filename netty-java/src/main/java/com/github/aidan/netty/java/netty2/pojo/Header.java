package com.github.aidan.netty.java.netty2.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang yi fei
 * @date 2019/2/26 18:03
 */
@Data
@ToString
public final class Header {
    private int crcCode = 0xabef0101;
    private int length;
    //消息长度
    private long sessionID;
    private byte type;//消息类型
    private byte priority;//消息优先级
    private Map<String, Object> attachment = new HashMap<String, Object>();//附件

}
