package com.github.aidan.netty.java.decoder.marshalling;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wang yi fei
 * @date 2019/3/1 9:35
 */
@Data
@ToString
public class People implements Serializable {

    private String name;
    private int age;
}
