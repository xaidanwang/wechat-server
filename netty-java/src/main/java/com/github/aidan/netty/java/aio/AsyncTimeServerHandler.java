package com.github.aidan.netty.java.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

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
            latch.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept(){
        asynchronousServerSocketChannel.accept(this,new AcceptCompletionChannel());
    }
}
