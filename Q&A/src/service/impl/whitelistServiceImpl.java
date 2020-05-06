package service.impl;

import dao.impl.whitelistDaoImpl;
import dao.whitelistDao;
import org.apache.log4j.Logger;
import pojo.course;
import pojo.student;
import service.whitelistService;

import java.util.List;

public class whitelistServiceImpl implements whitelistService {
    Logger logger=Logger.getLogger(whitelistServiceImpl.class);
    whitelistDao wd=new whitelistDaoImpl();
    @Override
    public boolean addListService(String sid, String cid) {
        boolean flag=wd.addListDao(sid,cid);
        if(flag){
            logger.debug("学生"+sid+"的"+cid+"课程权限设置成功");
        }
        else logger.debug("学生"+sid+"的"+cid+"课程权限设置失败");
        return flag;
    }

    @Override
    public boolean readedListService(String wid) {
        boolean flag=wd.readedListDao(wid);
        if(flag){
            logger.debug("设置"+wid+"已读");
        }
        else logger.debug("设置"+wid+"已读失败");
        return flag;
    }

    @Override
    public boolean delListService(String sid,String cid) {
        boolean flag=wd.delListDao(sid,cid);
        if(flag){
            logger.debug("解除学生"+sid+"在"+cid+"课程的权限成功");
        }
        else logger.debug("解除学生"+sid+"在"+cid+"课程的权限失败");
        return flag;
    }

    @Override
    public List<course> findCListService(String sid) {
        List<course> l=wd.findCListDao(sid);
        if(l!=null){
            logger.debug(sid+"已有课程为"+l);
        }else{
            logger.debug(sid+"尚无课程");
        }
        return l;
    }

    @Override
    public List<course> findCListService(String sid, int start, int count) {
        List<course> l=wd.findCListDao(sid,start,count);
        if(l!=null){
            logger.debug(sid+"已有课程为"+l+"(分页)");
        }else{
            logger.debug(sid+"尚无课程");
        }
        return l;
    }

    @Override
    public List<student> findSListService(String cid) {
        List<student> l=wd.findSListDao(cid);
        if(l!=null){
            logger.debug(cid+"已有学生为"+l);
        }else{
            logger.debug(cid+"尚无学生");
        }
        return l;
    }

    @Override
    public List<student> findSListService(String cid, int start, int count) {
        List<student> l=wd.findSListDao(cid,start,count);
        if(l!=null){
            logger.debug(cid+"已有学生为"+l+"(分页)");
        }else{
            logger.debug(cid+"尚无学生");
        }
        return l;
    }

    @Override
    public List<student> addSListService(String cid) {
        List<student> l=wd.addSListDao(cid);
        if(l!=null){
            logger.debug(cid+"可添加学生有"+l);
        }else{
            logger.debug(cid+"尚无可添加学生");
        }
        return l;
    }

    @Override
    public List<student> addSListService(String cid, int start, int count) {
        List<student> l=wd.addSListDao(cid,start,count);
        if(l!=null){
            logger.debug(cid+"可添加学生有"+l+"(分页)");
        }else{
            logger.debug(cid+"尚无可添加学生");
        }
        return l;
    }

    @Override
    public List<course> addCListService(String sid) {
        List<course> l=wd.addCListDao(sid);
        if(l!=null){
            logger.debug(sid+"可添加课程有"+l);
        }else{
            logger.debug(sid+"尚无可添加课程");
        }
        return l;
    }

    @Override
    public List<course> addCListService(String sid, int start, int count) {
        List<course> l=wd.addCListDao(sid,start,count);
        if(l!=null){
            logger.debug(sid+"可添加课程有"+l+"(分页)");
        }else{
            logger.debug(sid+"尚无可添加课程");
        }
        return l;
    }

    @Override
    public List<course> findCListService(String sid, String type, String var) {
        List<course> l=wd.findCListDao(sid,type,var);
        if(l!=null){
            logger.debug(sid+"根据 "+type+":"+var+" 查询已添加课程有"+l);
        }else{
            logger.debug(sid+"根据 "+type+":"+var+" 未查询到已添加课程");
        }
        return l;
    }

    @Override
    public List<course> findCListService(String sid, int start, int count, String type, String var) {
        List<course> l=wd.findCListDao(sid,start,count,type,var);
        if(l!=null){
            logger.debug(sid+"根据 "+type+":"+var+" （分页）查询已添加课程有"+l);
        }else{
            logger.debug(sid+"根据 "+type+":"+var+" （分页）未查询到已添加课程");
        }
        return l;
    }

    @Override
    public List<course> addCListService(String sid, String type, String var) {
        List<course> l=wd.addCListDao(sid,type,var);
        if(l!=null){
            logger.debug(sid+"根据 "+type+":"+var+" 查询可添加课程有"+l);
        }else{
            logger.debug(sid+"根据 "+type+":"+var+" 未查询到可添加课程");
        }
        return l;
    }

    @Override
    public List<course> addCListService(String sid, int start, int count, String type, String var) {
        List<course> l=wd.addCListDao(sid,start,count,type,var);
        if(l!=null){
            logger.debug(sid+"根据 "+type+":"+var+" （分页）查询可添加课程有"+l);
        }else{
            logger.debug(sid+"根据 "+type+":"+var+" （分页）未查询到可添加课程");
        }
        return l;
    }
}
