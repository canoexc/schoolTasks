package service;

import pojo.reply;
import pojo.uni;

import java.util.List;

public interface replyService {
    boolean addReplyService(reply r);
    boolean delReplyService(String rid,String mid);
    boolean upReplyService(reply r);
    boolean readed(String rid);
    List<uni> showReplyService(String type, String var);
    List<uni> showReplyService(String type,String var,int start,int count);
}
