package com.exp.server.utils;

import com.exp.server.service.CardImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class serverThread extends Thread {
    private Socket socket;
    private final static String CLASS_PATH="com.exp.server.service.";
    /*
    // 存放远程对象的缓存
    private Map<String,Object> remoteObjects = new HashMap<String, Object>() ;
    //注册服务：把一个远程对象放到缓存中
    public void register( String className,Object remoteObject) {

        remoteObjects.put( className,remoteObject) ;

    }*/
    public serverThread(Socket socket){
        this.socket=socket;
    }
    public void run(){
        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try{
        InputStream in = socket.getInputStream();
        ois = new ObjectInputStream(in);
        OutputStream out = socket.getOutputStream();
        oos = new ObjectOutputStream(out);
        //接收客户发送的Call 对象
        RemoteCall remotecallobj = (RemoteCall) ois.readObject();

        System.out.println(remotecallobj);
        // 调用相关对象的方法
        System.out.println("calling......");
        remotecallobj = invoke(remotecallobj);
        // 向客户发送包含了执行结果的remotecallobj 对象
        oos.writeObject(remotecallobj);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            try {
                if(ois!=null){
                    ois.close();
                }
                if (oos!=null){
                    oos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public RemoteCall invoke( RemoteCall call) {
        Object result = null;
        try {
            String className = call.getClassName();
            String methodName = call.getMethodName();
            Object[] params = call.getParams();
            Class<?> classType = Class.forName(className);
            Class<?>[] paramTypes = call.getParamTypes();
            Method method = classType.getMethod(methodName, paramTypes);
            // 从hashmap缓存中取出相关的远程对象Object
            //Object remoteObject =remoteObjects.get(className);
            ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
            Object remoteObject = ac.getBean("card");
            if (remoteObject == null) {
                throw new Exception(className + " 的远程对象不存在");
            } else {
                result = method.invoke(remoteObject, params);
                System.out.println("远程调用结束:remotObject:"+remoteObject.toString()+",params:"+params.toString());
            }
        } catch (Exception e) { System.out.println("错误："+e.getMessage()); }
        call.setResult(result);

        return call;
    }
}
