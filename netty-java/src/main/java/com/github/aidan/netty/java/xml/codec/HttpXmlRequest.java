package com.github.aidan.netty.java.xml.codec;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author wang yi fei
 * @date 2019/2/22 11:19
 */
public class HttpXmlRequest {

    private FullHttpRequest request;
    private Object body;

    public HttpXmlRequest(FullHttpRequest request, Object body) {
        this.request = request;
        this.body = body;
    }

    /**
     * @return the request
     */
    public final FullHttpRequest getRequest() {
        return request;
    }

    /**
     * @param request
     *            the request to set
     */
    public final void setRequest(FullHttpRequest request) {
        this.request = request;
    }

    /**
     * @return the object
     */
    public final Object getBody() {
        return body;
    }

    /**
     * @param object
     *            the object to set
     */
    public final void setBody(Object body) {
        this.body = body;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HttpXmlRequest [request=" + request + ", body =" + body + "]";
    }
}
