package service.impl;

import dao.impl.messageDaoImpl;
import dao.messageDao;
import org.apache.log4j.Logger;
import pojo.message;
import pojo.uni;
import service.messageService;

import java.util.ArrayList;
import java.util.List;


public class messageServiceImpl implements messageService {
    Logger logger= Logger.getLogger(messageServiceImpl.class);
    messageDao md=new messageDaoImpl();
    @Override
    public boolean addMessageService(message m) {
        boolean flag=md.addMessageDao(m);
        if(flag)logger.debug("学生"+m.getSid()+"成功在课程"+m.getCid()+"添加一条留言");
        else logger.debug("学生"+m.getSid()+"在课程"+m.getCid()+"添加新留言失败");
        return flag;
    }

    @Override
    public boolean delMessageService(String mid) {
        boolean flag=md.delMessageDao(mid);
        if(flag)logger.debug("删除留言"+mid+"成功");
        else logger.debug("删除留言"+mid+"失败");
        return flag;
    }

    @Override
    public boolean upMessageService(message m) {
        boolean flag=md.upMessageDao(m);
        if(flag)logger.debug("更新留言"+m+"成功");
        else logger.debug("更新留言"+m+"失败");
        return flag;
    }

    @Override
    public List<uni> showMesService(String type, String var, String var1) {
        List<uni> l=new ArrayList<>();
        l=md.showMesDao(type,var,var1);
        if(l!=null){
            logger.debug("根据"+type+":"+"var:"+var+"var1:"+var1+"查看留言:"+l);
        }else logger.debug("根据"+type+":"+"var:"+var+"var1:"+var1+"查找尚无留言");
        return l;
    }

    @Override
    public List<uni> showMesService(String type, String var, String var1, int start, int count) {
        List<uni> l=new ArrayList<>();
        l=md.showMesDao(type,var,var1,start,count);
        if(l!=null){
            logger.debug("(分页)根据"+type+":"+"var:"+var+"var1:"+var1+"查看留言:"+l);
        }else logger.debug("(分页)根据"+type+":"+"var:"+var+"var1:"+var1+"查找尚无留言");
        return l;
    }
}
