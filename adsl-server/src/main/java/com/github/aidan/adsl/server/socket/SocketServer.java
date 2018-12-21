package com.github.aidan.adsl.server.socket;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer  {

    private ServerSocket serverSocket;

    public SocketServer(int port){
        try {
            serverSocket = new ServerSocket(port);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startSocket(){
        while (true){
            try {
                Socket socket = serverSocket.accept();
                new Thread(new MessageHandler(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                System.out.println("startSocket() 异常");
            }
        }
    }
}
