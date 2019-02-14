package com.github.aidan.netty.java.aio;

/**
 * @author wang yi fei
 * @date 2019/2/14 14:03
 */
public class TimeServer {


    public static void main(String[] args) {

        int port = 8080;
        if (args != null && args.length>0){

            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException n){
            }
        }
        AsyncTimeServerHandler asyncTimeServerHandler = new AsyncTimeServerHandler(port);

        new Thread(asyncTimeServerHandler,"AIO-AsyncTimeServerHandler--001").start();
    }
}
