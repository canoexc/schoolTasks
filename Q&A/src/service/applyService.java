package service;

import pojo.apply;
import pojo.course;
import pojo.stuandcos;

import java.util.List;

public interface applyService {
    boolean addApplyService(apply a);
    boolean changeStatusService(String type,String pid);
    List<stuandcos> showApplyService(String tid, String type);
    List<stuandcos> showApplyService(String tid,String type,int start,int count);
    List<course> showRappService(String sid, String type);
    List<course> showRappService(String sid,String type,int start,int count);
}
