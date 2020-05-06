package dao;

import pojo.course;
import pojo.student;

import java.util.List;

public interface whitelistDao {
    boolean addListDao(String sid,String cid);
    boolean readedListDao(String wid);
    boolean delListDao(String sid,String cid);
    List<course> findCListDao(String sid);
    List<course> findCListDao(String sid,int start,int count);
    List<student> findSListDao(String cid);
    List<student> findSListDao(String cid,int start,int count);
    List<student> addSListDao(String cid);
    List<student> addSListDao(String cid,int start,int count);
    List<course> addCListDao(String sid);
    List<course> addCListDao(String sid,int start,int count);
    List<course> findCListDao(String sid,String type,String var);
    List<course> findCListDao(String sid,int start,int count,String type,String var);
    List<course> addCListDao(String sid,String type,String var);
    List<course> addCListDao(String sid,int start,int count,String type,String var);
}
