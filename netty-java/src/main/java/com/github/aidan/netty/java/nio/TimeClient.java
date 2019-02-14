package com.github.aidan.netty.java.nio;

/**
 * @author wang yi fei
 * @date 2019/2/13 16:01
 */
public class TimeClient {


    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length>0){

            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException n){
            }
        }
        new Thread(new TimeClientHandle("127.0.0.1",port)).start();
    }
}
