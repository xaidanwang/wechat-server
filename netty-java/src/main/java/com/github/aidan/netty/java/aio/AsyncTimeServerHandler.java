package com.github.aidan.netty.java.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wang yi fei
 * @date 2019/2/14 14:04
 */
public class AsyncTimeServerHandler implements Runnable {


    private int port;

    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsyncTimeServerHandler(int port) {
        this.port = port;
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("The time server ;is start in port :"+ port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
            //latch.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept(){
        /**
         * 调用AsynchronousServerSocketChannel.accept() 后，如果有新的客户端接入后，
         * 系统会回调我们传入的CompletionHandler实例的complete方法，表示新的客户端已经接入成功。因为一个调用AsynchronousServerSocketChannel
         * 可以接受成千上万个客户端，所以需要继续调用它的accept方法，接收其他的客户端连接，最终形成一个循环。每当接收一个客户读连接成功后，在异步
         * 接收新的客户端连接。
         */
        asynchronousServerSocketChannel.accept(this,new AcceptCompletionChannel());
    }
}
