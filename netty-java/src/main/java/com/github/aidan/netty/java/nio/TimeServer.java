package com.github.aidan.netty.java.nio;

/**
 * @author wang yi fei
 * @date 2019/2/13 14:03
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

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);

        new Thread(timeServer,"NIO-MultiplexerTimeServer--001").start();

    }
}
