package com.github.aidan.netty.java.netty3.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wang yi fei
 * @date 2019/2/28 16:42
 */
@ToString
@Data
public class NettyMessage implements Serializable {

    private Header header;
    private Object body;
}
