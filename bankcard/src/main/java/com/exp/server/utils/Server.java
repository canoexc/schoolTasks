package com.exp.server.utils;

import com.exp.server.service.CardImpl;
import com.exp.server.utils.RemoteCall;
import com.exp.server.utils.serverThread;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Server {
    private final static String CLASS_PATH="com.exp.server.service.";
    public static void main( String args[ ]) throws Exception {
        ServerSocket server = null ;
        Socket socket=null;
        server = new ServerSocket(8000);
        Executor executor=Executors.newFixedThreadPool(10);
        System.out.println("服务器零号机启动。。。");
        while (true){
            socket=server.accept();
            executor.execute(new serverThread(socket));
        }
    }
}