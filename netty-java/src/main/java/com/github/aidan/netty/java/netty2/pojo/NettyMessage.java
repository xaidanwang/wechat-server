package com.github.aidan.netty.java.netty2.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @author wang yi fei
 * @date 2019/2/26 18:04
 */
@Data
@ToString
public final class NettyMessage {

    private Header header;
    private Object body;

}
