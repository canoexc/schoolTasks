package service.impl;

import dao.adminDao;
import dao.impl.adminDaoImpl;
import pojo.admin;
import service.adminService;

import org.apache.log4j.Logger;

public class adminServiceImpl implements adminService {
    Logger logger=Logger.getLogger(adminServiceImpl.class);
    adminDao ad=new adminDaoImpl();
    @Override
    public admin checkAdminLoginService(String aname, String pwd) {
        logger.debug(aname+"发起登录请求");
        admin a=ad.checkAdminLoginDao(aname,pwd);
        if(a!=null){
            logger.debug(aname+"登录成功");
        }else {
            logger.debug(aname+"登录失败");
        }
        return a;
    }
    public boolean pwdChange(String aid, String pwd){
        boolean flag=ad.pwdChange(aid,pwd);
        if(flag)logger.debug("管理员"+aid+"修改密码成功");
        else logger.debug("管理员"+aid+"修改密码失败");
        return flag;
    }
}
