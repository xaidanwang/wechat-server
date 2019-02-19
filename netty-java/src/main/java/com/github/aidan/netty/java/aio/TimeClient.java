package com.github.aidan.netty.java.aio;

/**
 * @author wang yi fei
 * @date 2019/2/14 15:48
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8081;
        if (args != null && args.length>0){

            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException n){
            }
        }
        new Thread(new AsyncTimeClientHandler("127.0.0.1",port)).start();
    }
}
