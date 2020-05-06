package com.exp.client;

import com.exp.server.service.Card;
import com.exp.server.service.CardImpl;
import com.exp.server.utils.DynamicProxyFactory;
import com.exp.server.utils.randomIP;

import java.lang.reflect.UndeclaredThrowableException;
import java.net.ConnectException;
import java.util.Scanner;

public class Atm {
    private final static String CLASS_PATH="com.exp.server.service.";
    private String uid=null;
    public static void main(String[] args) throws Exception {
        randomIP port=new randomIP();
        Atm atm=new Atm();
        int p=port.serverPort();
        if(p<0){
            System.out.println("现无可用端口");
            System.out.println("自动结束程序");
            return;
        }
        Card c=new CardImpl();
        c = DynamicProxyFactory.getProxy(Card.class, port.host, p);
        /*
        int m=c.withdraw("jack",100);
        System.out.println(m);
        */
        /*
        int m =0;
        m=c.query("jack");
        System.out.println("动态代理+网络封装方式的远程执行结果为："+m+"端口号："+p);
        */
        Scanner scan=new Scanner(System.in);
        while(true) {
            System.out.println("****************************");
            System.out.println("       欢迎使用ATM系统");
            System.out.println("        请登录后使用");
            System.out.println("****************************");
            System.out.println("请输入用户名");
            String id = scan.nextLine();
            System.out.println("请输入密码");
            String pwd = scan.nextLine();
            p=port.serverPort();
            if(p<0){
                System.out.println("现无可用端口");
                System.out.println("自动结束程序");
                return;
            }
            c = DynamicProxyFactory.getProxy(Card.class, port.host, p);
            int ok=c.state(id);
            if(ok==-9999)
            {
                System.out.println("该账号已锁定");
                continue;
            }
            p=port.serverPort();
            if(p<0){
                System.out.println("现无可用端口");
                System.out.println("自动结束程序");
                return;
            }
            c = DynamicProxyFactory.getProxy(Card.class, port.host, p);
            int res=c.login(id,pwd);
            if (res == 1&&ok!=-9999) {
                atm.uid=id;
                break;
            }
            p=port.serverPort();
            if(p<0){
                System.out.println("现无可用端口");
                System.out.println("自动结束程序");
                return;
            }
            c = DynamicProxyFactory.getProxy(Card.class, port.host, p);
            ok=c.state(id);
            if(res == 0&&ok!=-9999){
                System.out.println("已连续输错"+ok+"次密码，还有"+(3-ok)+"次机会");
            }else if(ok==-9999){
                System.out.println("连续输错3次密码，账号已锁定");
            }else if(res==-1)System.out.println("操作异常");
        }
        System.out.println("登录成功！");
        while(true){
            int key=atm.mainMenu();
            int ck=0;
            if (key==-9999)break;
            switch (key){
                case 1:
                    p=port.serverPort();
                    if(p<0){
                        System.out.println("现无可用端口");
                        System.out.println("自动结束程序");
                        return;
                    }
                    c = DynamicProxyFactory.getProxy(Card.class, port.host, p);
                    System.out.println("当前余额为"+c.query(atm.uid)+"元");
                    key=atm.subMenu();
                    break;
                case 2:
                    p=port.serverPort();
                    if(p<0){
                        System.out.println("现无可用端口");
                        System.out.println("自动结束程序");
                        return;
                    }
                    c = DynamicProxyFactory.getProxy(Card.class, port.host, p);
                    System.out.println("当前余额为"+c.query(atm.uid)+"元");
                    System.out.println("请输入取款金额");
                    int m=scan.nextInt();
                    p=port.serverPort();
                    if(p<0){
                        System.out.println("现无可用端口");
                        System.out.println("自动结束程序");
                        return;
                    }
                    c = DynamicProxyFactory.getProxy(Card.class, port.host, p);
                    ck=c.withdraw(atm.uid,m);
                    if(ck==-9999){
                        System.out.println("余额不足！取款失败");
                    }else if(ck==0){
                        System.out.println("系统异常！");
                    }else System.out.println("操作成功！");
                    key=atm.subMenu();
                    break;
                case 3:
                    System.out.println("请输入存款金额");
                    int n=scan.nextInt();
                    p=port.serverPort();
                    if(p<0){
                        System.out.println("现无可用端口");
                        System.out.println("自动结束程序");
                        return;
                    }
                    c = DynamicProxyFactory.getProxy(Card.class, port.host, p);
                    ck=c.topUp(atm.uid,n);
                    if(ck==0){
                        System.out.println("系统异常！");
                    }else System.out.println("操作成功！");
                    key=atm.subMenu();
                    break;
            }
            if(key==-9999)
                break;

        }
    }
    public int mainMenu(){
        System.out.println("****************************");
        System.out.println("        1.查询余额");
        System.out.println("        2.取款");
        System.out.println("        3.存款");
        System.out.println("        4.退出系统");
        System.out.println("****************************");
        System.out.println("请输入操作代号");
        Scanner scan=new Scanner(System.in);
        int key=scan.nextInt();
        if(key==4)key=-9999;
        return key;
    }
    public int subMenu(){
        System.out.println("****************************");
        System.out.println("        1.返回主菜单");
        System.out.println("        2.退出系统");
        System.out.println("****************************");
        System.out.println("请输入操作代号");
        Scanner scan=new Scanner(System.in);
        int key=scan.nextInt();
        if(key==2)key=-9999;
        return key;
    }
}
