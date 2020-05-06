package service.impl;

import dao.applyDao;
import dao.impl.applyDaoImpl;
import org.apache.log4j.Logger;
import pojo.apply;
import pojo.course;
import pojo.stuandcos;
import service.applyService;

import java.util.ArrayList;
import java.util.List;

public class applyServiceImpl implements applyService {
    Logger logger=Logger.getLogger(applyServiceImpl.class);
    applyDao ad=new applyDaoImpl();
    @Override
    public boolean addApplyService(apply a) {
        boolean flag=ad.addApplyDao(a);
        if(flag)logger.debug(a.getSid()+"申请课程"+a.getCid()+"发送成功");
        else logger.debug(a.getSid()+"申请课程"+a.getCid()+"发送失败");
        return flag;
    }

    @Override
    public boolean changeStatusService(String type, String pid) {
        boolean flag=ad.changeStatusDao(type,pid);
        if(flag)logger.debug("操作申请"+pid+"成功");
        else logger.debug("操作申请"+pid+"失败");
        return flag;
    }

    @Override
    public List<stuandcos> showApplyService(String tid, String type) {
        List<stuandcos> l=new ArrayList<>();
        l=ad.showApplyDao(tid,type);
        if(l!=null){
            logger.debug("教师编号"+tid+"查看的"+type+"申请有:"+l);
        }else logger.debug("教师编号"+tid+"尚无"+type+"申请");
        return l;
    }

    @Override
    public List<stuandcos> showApplyService(String tid, String type, int start, int count) {
        List<stuandcos> l=new ArrayList<>();
        l=ad.showApplyDao(tid,type,start,count);
        if(l!=null){
            logger.debug("(分页)教师编号"+tid+"查看的"+type+"申请有:"+l);
        }else logger.debug("(分页)教师编号"+tid+"尚无"+type+"申请");
        return l;
    }

    @Override
    public List<course> showRappService(String tid, String type) {
        List<course> l=new ArrayList<>();
        l=ad.showRappDao(tid,type);
        if(l!=null){
            logger.debug("学生编号"+tid+"查看的"+type+"申请回复有:"+l);
        }else logger.debug("学生编号"+tid+"尚无"+type+"申请回复");
        return l;
    }

    @Override
    public List<course> showRappService(String sid, String type, int start, int count) {
        List<course> l=new ArrayList<>();
        l=ad.showRappDao(sid,type,start,count);
        if(l!=null){
            logger.debug("(分页)学生编号"+sid+"查看的"+type+"申请回复有:"+l);
        }else logger.debug("(分页)学生编号"+sid+"尚无"+type+"申请回复");
        return l;
    }
}
