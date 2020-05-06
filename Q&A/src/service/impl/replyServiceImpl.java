package service.impl;

import dao.impl.replyDaoImpl;
import dao.replyDao;
import org.apache.log4j.Logger;
import pojo.reply;
import pojo.uni;
import service.replyService;

import java.util.ArrayList;
import java.util.List;

public class replyServiceImpl implements replyService {
    Logger logger=Logger.getLogger(replyServiceImpl.class);
    replyDao rd=new replyDaoImpl();
    @Override
    public boolean addReplyService(reply r) {
        boolean flag=rd.addReplyDao(r);
        if(flag)logger.debug("回复"+r.getMid()+"成功");
        else logger.debug("回复"+r.getMid()+"失败");
        return flag;
    }

    @Override
    public boolean delReplyService(String rid, String mid) {
        boolean flag=rd.delReplyDao(rid,mid);
        if(flag)logger.debug("删除"+rid+"成功");
        else logger.debug("删除"+rid+"失败");
        return flag;
    }

    @Override
    public boolean upReplyService(reply r) {
        boolean flag=rd.upReplyDao(r);
        if(flag)logger.debug("更新"+r.getMid()+"成功");
        else logger.debug("更新"+r.getMid()+"失败");
        return flag;
    }

    @Override
    public boolean readed(String rid) {
        boolean flag=rd.readed(rid);
        if(flag)logger.debug("已读"+rid);
        else logger.debug("已读"+rid+"失败");
        return flag;
    }

    @Override
    public List<uni> showReplyService(String type, String var) {
        List<uni> l=new ArrayList<>();
        l=rd.showReplyDao(type,var);
        if(l!=null)logger.debug("根据"+type+" var:"+var+"查询回复"+l);
        else logger.debug("根据"+type+" var:"+var+"未查询到相关回复");
        return l;
    }

    @Override
    public List<uni> showReplyService(String type, String var, int start, int count) {
        List<uni> l=new ArrayList<>();
        l=rd.showReplyDao(type,var);
        if(l!=null)logger.debug("(分页)根据"+type+" var:"+var+"查询回复"+l);
        else logger.debug("(分页)根据"+type+" var:"+var+"未查询到相关回复");
        return l;
    }
}
