package dao;

import pojo.message;
import pojo.uni;

import java.util.List;

public interface messageDao {
    boolean addMessageDao(message m);
    boolean delMessageDao(String mid);
    boolean upMessageDao(message m);
    List<uni> showMesDao(String type, String var, String var1);
    List<uni> showMesDao(String type,String var,String var1,int start,int count);

}
