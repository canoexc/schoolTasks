package service.impl;

import dao.impl.studentDaoImpl;
import dao.studentDao;
import org.apache.log4j.Logger;
import pojo.student;
import service.studentService;



public class studentServiceImpl implements studentService {
    Logger logger= Logger.getLogger(studentServiceImpl.class);
    studentDao sd=new studentDaoImpl();
    @Override
    public student checkStudentLoginService(String sid, String pwd) {
        logger.debug(sid+"发起登录请求");
        student s=sd.checkStudentLoginDao(sid,pwd);
        if(s!=null){
            logger.debug(sid+"登录成功");
        }else {
            logger.debug(sid+"登录失败");
        }
        return s;
    }

    @Override
    public int addStudentService(String sname, String pwd) {
        int sid=sd.addStudentDao(sname,pwd);
        if(sid>0)
        logger.debug(sid+"注册成功！");
        else logger.debug(sid+"注册失败！");
        return sid;
    }

    @Override
    public boolean pwdChange(String sid, String pwd) {
        boolean flag=sd.pwdChangeDao(sid,pwd);
        if(flag)logger.debug("学生"+sid+"密码修改成功");
        else logger.debug("学生"+sid+"密码修改失败");
        return flag;
    }
}
