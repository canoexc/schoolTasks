package com.exp.server.utils;

import com.exp.server.dao.CardDao;
import com.exp.server.dao.CardDaoImpl;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class loginCheck {
    CardDao cd=new CardDaoImpl();
    @AfterReturning(value = "execution(* com.exp.server.service.CardImpl.login(..)) && args(id,pwd)")
    public void checkResult(String id,String pwd){
        int res=cd.queryLockDao(id);
        if(res>=3)
            cd.deadLock(id);
        }
}
