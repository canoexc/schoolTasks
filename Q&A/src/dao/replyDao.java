package dao;

import pojo.reply;
import pojo.uni;

import java.util.List;

public interface replyDao {
    boolean addReplyDao(reply r);
    boolean delReplyDao(String rid,String mid);
    boolean upReplyDao(reply r);
    boolean readed(String rid);
    List<uni> showReplyDao(String type, String var);
    List<uni> showReplyDao(String type,String var,int start,int count);
}
