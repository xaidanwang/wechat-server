package com.github.aidan.netty.java.decoder.marshalling;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wang yi fei
 * @date 2019/3/1 9:39
 */
@Data
@ToString
public class Car implements Serializable {

    private String vicehno;

    private Date date;
}
