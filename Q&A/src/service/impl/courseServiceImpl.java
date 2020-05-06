package service.impl;

import dao.courseDao;
import dao.impl.courseDaoImpl;
import org.apache.log4j.Logger;
import pojo.course;
import service.courseService;

import java.util.List;

public class courseServiceImpl implements courseService {
    Logger logger=Logger.getLogger(courseServiceImpl.class);
    courseDao cd=new courseDaoImpl();
    @Override
    public List<course> showAllCourseService() {
        List<course> lc=cd.showAllCourseDao();
        logger.debug("查询所有课程"+lc);
        return lc;
    }

    @Override
    public List<course> showSubCourseService(String type, String var) {
        List<course> lc=cd.showSubCourseDao(type, var);
        logger.debug("根据 "+type+":"+var+" 分类查询课程"+lc);
        return lc;
    }

    @Override
    public List<course> showSubCourseService(String type, String var, int tid) {
        List<course> lc=cd.showSubCourseDao(type,var,tid);
        logger.debug("教师tid："+tid+" 根据"+type+"查询自己课程");
        return lc;
    }

    @Override
    public List<course> showAllCourseService(int start, int count) {
        List<course> lc=cd.showAllCourseDao(start,count);
        logger.debug("分页/查询所有课程"+lc);
        return lc;
    }

    @Override
    public List<course> showSubCourseService(String type, String var, int start, int count) {
        List<course> lc=cd.showSubCourseDao(type, var, start, count);
        logger.debug("根据 "+type+":"+var+" 分类查询课程"+lc);
        return lc;
    }

    @Override
    public List<course> showSubCourseService(String type, String var, int tid, int start, int count) {
        List<course> lc=cd.showSubCourseDao(type,var,tid,start,count);
        logger.debug("教师tid："+tid+" 根据"+type+"分页查询自己课程");
        return lc;
    }

    @Override
    public boolean addCourseService(course c) {
        boolean flag=cd.addCourseDao(c);
        if(flag)
        logger.debug("添加"+c.getCname()+"课程成功");
        else  logger.debug("添加"+c.getCname()+"课程失败");
        return flag;
    }

    @Override
    public boolean delCourseService(String cid) {
        boolean flag=cd.delCourseDao(cid);
        if(flag)
            logger.debug("删除cid为"+cid+"课程成功");
        else  logger.debug("删除cid为"+cid+"课程失败");
        return flag;
    }

    @Override
    public boolean upCourseService(course c) {
        boolean flag=cd.upCourseDao(c);
        if(flag)
            logger.debug("更新"+c.getCname()+"课程成功");
        else  logger.debug("更新"+c.getCname()+"课程失败");
        return flag;
    }
}
