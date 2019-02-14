package com.github.aidan.netty.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wang yi fei
 * @date 2019/2/13 14:06
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel servChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     * @param port
     */
    public MultiplexerTimeServer(int port){
        try {
            //1.打开ServerSocketChannel,用于监听客户端的连接
            servChannel = ServerSocketChannel.open();
            //2.绑定监听端口和最大连接数，设置非阻塞式处理
            servChannel.socket().bind(new InetSocketAddress(port),1024);
            servChannel.configureBlocking(false);
            //3.创建多路复用器
            selector = Selector.open();
            //4.ServerSocketChannel 注册到selector 监听 SelectionKey.OP_ACCEPT 操作位 如果资源初始化失败，则退出
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        /**
         * 5.多路复用器在线程run 方法的无线循环体内轮训准备就绪的key
         */
        while (!stop){
            try {
                // 阻塞式的方法
                /**
                 * 如果 timeout为正，则select(long timeout)在等待有通道被选择时至多会阻塞timeout毫秒
                 * 如果timeout为零，则永远阻塞直到有至少一个通道准备就绪。
                 * timeout不能为负数
                 */
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    }catch (Exception e){
                        if (key != null){
                            key.cancel();
                            if (key.channel()!= null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        if (selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException{

        if (key.isValid()){
            //处理新接入的请求消息
            if (key.isAcceptable()){
                //Acccept the new  connection
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //添加一个新的连接到 selector
                sc.register(selector,SelectionKey.OP_READ);
            }
            if (key.isReadable()){
                //读取数据
                SocketChannel sc = (SocketChannel)key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                //读取客户端的请求码流放入readBuffer 缓冲区中，由于SocketChannel 为非阻塞的 所以
                //read 是非阻塞的，使用返回值判断，看读取到的字节数
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    //get 方法将readBuffer 的数据字节缓冲到bytes 中
                    readBuffer.get(bytes);
                    //decode 接码 bytes 中的数据字节信息
                    String body = new String(bytes,"UTF-8");
                    System.out.println("The time server receive order :"+ body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"DAD ORDER";

                    doWrite(sc,currentTime);
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

    private void doWrite(SocketChannel channel, String response) throws IOException {

        if (response != null && response.trim().length()> 0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            //put 方法是将byte[] bytes 中的字节流放入 writeBuffer 中
            writeBuffer.put(bytes);
            System.out.println("调用 doWrite  response :"+response);
            writeBuffer.flip();
            //SocketChannel 将 writeBuffer 缓冲中的字节数据 写出
            channel.write(writeBuffer);
        }
    }
}
