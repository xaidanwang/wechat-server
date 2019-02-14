package com.github.aidan.netty.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wang yi fei
 * @date 2019/2/13 16:02
 */
public class TimeClientHandle implements  Runnable {

    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    public TimeClientHandle(String host, int port) {
        this.host = host == null ? "127.0.0.1": host;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {

        //判断连接结果
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;

                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    handleInput(key);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //多路复用器关闭后，所有注册上面的Channel 和 Pipe 等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doConnect() throws IOException {
        //如果 连接成功，注册到多路复用器上，发送请求消息，读应答
        if (socketChannel.connect(new InetSocketAddress(host,port))){
            //注册到多路复用器上
            socketChannel.register(selector, SelectionKey.OP_READ);
            //发送请求消息，读应答
        }else {
            //连接失败，向Reactor 线程的 多路复用器 注册 SelectionKey.OP_CONNECT 事件
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()){
            //判断是否连接成功
            SocketChannel sc = (SocketChannel)key.channel();
            if (key.isConnectable()){
                if (sc.finishConnect()){
                    sc.register(selector,SelectionKey.OP_READ);
                    doWriter(sc);
                }else {
                    //连接失败，退出进程
                    System.exit(1);
                }
            }
            if (key.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    System.out.println("NOW IS :"+body);
                    this.stop = true;
                }else if (readBytes < 0){
                    //对端链路关闭
                    key.cancel();
                    sc.close();
                }else {
                    //读取到0字节忽略
                }
            }
        }
    }

    private void  doWriter(SocketChannel sc) throws IOException {

        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writerBuffer = ByteBuffer.allocate(1024);
        writerBuffer.put(req);
        writerBuffer.flip();
        sc.write(writerBuffer);
        if (!writerBuffer.hasRemaining()){
            System.out.println("Send order 2 server succeed.");
        }
    }
}
