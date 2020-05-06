package service;

import pojo.message;
import pojo.uni;

import java.util.List;

public interface messageService {
    boolean addMessageService(message m);
    boolean delMessageService(String mid);
    boolean upMessageService(message m);
    List<uni> showMesService(String type, String var, String var1);
    List<uni> showMesService(String type,String var,String var1,int start,int count);
}
