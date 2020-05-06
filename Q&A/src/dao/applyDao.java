package dao;

import pojo.apply;
import pojo.course;
import pojo.stuandcos;

import java.util.List;

public interface applyDao {
    boolean addApplyDao(apply a);
    boolean changeStatusDao(String type,String pid);
    List<stuandcos> showApplyDao(String tid,String type);
    List<stuandcos> showApplyDao(String tid,String type,int start,int count);
    List<course> showRappDao(String sid, String type);
    List<course> showRappDao(String sid,String type,int start,int count);
}
