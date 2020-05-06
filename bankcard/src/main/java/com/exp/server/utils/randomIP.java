package com.exp.server.utils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class randomIP {
    public static HashMap<Integer,Integer> serverWeightMap=new HashMap<>();
    public static String host;
    public static int check=0;
    public static Integer pos=0;
    static {
        Properties props = new Properties();
        try {
            props.load(ClassLoader.getSystemResourceAsStream("prop.properties"));
            serverWeightMap.put(Integer.parseInt(props.getProperty("port1")),1);
            serverWeightMap.put(Integer.parseInt(props.getProperty("port2")),2);
            host=props.getProperty("host");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int serverPort(){
        Map<Integer,Integer> serverMap=new HashMap<>();
        serverMap.putAll(serverWeightMap);
        Set<Integer> keySet=serverMap.keySet();
        List<Integer> keyList=new ArrayList<>();
        keyList.addAll(keySet);
        //java.util.Random random=new java.util.Random();
        //int pos=random.nextInt(keyList.size());
        int port=0;
        synchronized (pos){
            if(pos>=keySet.size()){
                pos=0;
            }
            port=keyList.get(pos);
            pos++;
        }
        if(checkPort(port)==1)
        {
            check=0;
            System.out.println("启用"+port+"端口");
            return port;
        }else if(check!=2){
            check++;
            return serverPort();
        }else {
            return -9999;
        }
    }
    public static int checkPort(int port){
        Socket socket=null;
        int ok=0;
        try{
            socket=new Socket(host,port);
            ok=1;
        }  catch (IOException e) {
            //e.printStackTrace();
            System.out.println(port+"端口无法连接");
        }finally {
            if(socket!=null){
                try {
                    socket.close();
                }catch (IOException e){
                    System.out.println("无法关闭服务器连接"+e);
                }
            }
        }
        return ok;
    }
}
