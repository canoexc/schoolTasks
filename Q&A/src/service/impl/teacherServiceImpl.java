package service.impl;

import dao.impl.teacherDaoImpl;
import dao.teacherDao;
import org.apache.log4j.Logger;
import pojo.teacher;
import service.teacherService;

import java.util.List;

public class teacherServiceImpl implements teacherService {
    Logger logger=Logger.getLogger(teacherServiceImpl.class);
    teacherDao td=new teacherDaoImpl();
    @Override
    public teacher checkTeacherLoginService(String tname, String pwd) {
        logger.debug(tname+"发起登录请求");
        teacher t=td.checkTeacherLoginDao(tname,pwd);
        if(t!=null){
            logger.debug(tname+"登录成功");
        }else {
            logger.debug(tname+"登录失败");
        }
        return t;
    }

    @Override
    public List<teacher> allTeacherService() {
        List<teacher> lt=td.allTeacherDao();
        return lt;
    }

    @Override
    public List<teacher> allSubTeacherService(String type, String var) {
        List<teacher> lt=td.allSubTeacherDao(type, var);
        return lt;
    }

    //获取教师信息
    @Override
    public List<teacher> showTeacherService(int start,int count) {
        List<teacher> lt=td.showTeacherDao(start,count);
        return lt;
    }

    @Override
    public List<teacher> subshowTeacherService(String type, String var,int start,int count) {
        List<teacher> lt=td.subShowTeacherDao(type,var,start,count);
        return lt;
    }

    //添加教师
    @Override
    public boolean addTeacherService(teacher t) {
        boolean flag=td.addTeacherDao(t);
        return flag;
    }
    //删除教师
    @Override
    public boolean delTeacherService(String tid) {
        boolean flag=td.delTeacherDao(tid);
        return flag;
    }

    @Override
    public boolean upTeacherService(teacher t) {
        boolean flag=td.upTeacherDao(t);
        return flag;
    }

    @Override
    public boolean pwdChange(String tid, String pwd) {
        boolean flag=td.pwdChange(tid,pwd);
        return flag;
    }

}
