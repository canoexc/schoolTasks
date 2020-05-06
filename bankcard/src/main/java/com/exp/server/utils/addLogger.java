package com.exp.server.utils;

import com.exp.server.dao.CardDao;
import com.exp.server.dao.CardDaoImpl;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.apache.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//@Aspect
public class addLogger {
    private String log4jConfPath = "src/main/resources/log4j.properties";
    //System.getProperty("user.dir")+ File.separator+
    CardDao cd=new CardDaoImpl();
    //@AfterReturning(value = "execution(* com.exp.server.service.CardImpl.withdraw(..)) && args(id,m)",returning="state")
    public void printLogger(String id,int m,int state){
        Logger logger= Logger.getLogger(addLogger.class);
        PropertyConfigurator.configure(log4jConfPath);
        if(state==-9999)
            return;
        int rem=cd.queryDao(id);
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowtime = d.format(new Date());
        logger.debug(nowtime+" 用户"+id+" 取款"+m+"元 "+" 取款前余额"+(m+rem)+"元 取款后余额"+rem+"元");
        cd.loggerDao(id,m);
    }
}
