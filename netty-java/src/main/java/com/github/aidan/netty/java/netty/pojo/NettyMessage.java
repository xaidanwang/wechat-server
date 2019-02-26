package com.github.aidan.netty.java.netty.pojo;

/**
 * @author wang yi fei
 * @date 2019/2/23 18:25
 */
public class NettyMessage {

    private Header header;
    private Object body;

    public Header getHeader() {
        return header;
    }

    public Object getBody() {
        return body;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage{" +
                "heafer=" + header +
                '}';
    }
}
