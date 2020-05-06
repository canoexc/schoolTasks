package com.exp.server.utils;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server1 {
    private final static String CLASS_PATH="com.exp.server.service.";
    public static void main( String args[ ]) throws Exception {
        ServerSocket server = null ;
        Socket socket=null;
        server = new ServerSocket(3104);
        Executor executor= Executors.newFixedThreadPool(10);
        System.out.println("服务器初号机启动。。。");
        while (true){
            socket=server.accept();
            executor.execute(new serverThread(socket));
        }
    }
}
