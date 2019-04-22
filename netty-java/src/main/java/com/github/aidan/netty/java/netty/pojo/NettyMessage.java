package com.github.aidan.netty.java.netty.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wang yi fei
 * @date 2019/2/23 18:25
 */
@Data
@ToString
public class NettyMessage{
    private Header header;
    private Object body;

}
