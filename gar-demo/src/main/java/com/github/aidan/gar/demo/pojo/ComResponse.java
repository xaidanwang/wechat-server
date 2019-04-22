package com.github.aidan.gar.demo.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wang yi fei
 * @date 2019/3/1 14:38
 */
@Data
@ToString
public class ComResponse<T> implements Serializable {

    private String topic;

    private String method;

    private int result;

    private String transactionId;

    private T data;
}
