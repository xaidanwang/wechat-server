package com.github.aidan.adsl.server.util;


import java.util.UUID;

/**
 * Created by Administrator on 2018/7/24.
 */
public class UuidUtils {
    public static String getUUID(){
       return UUID.randomUUID().toString().replace("-", "");
    }
}
