package service;

import pojo.course;
import pojo.student;

import java.util.List;

public interface whitelistService {
    boolean addListService(String sid,String cid);
    boolean readedListService(String wid);
    boolean delListService(String sid,String cid);
    List<course> findCListService(String sid);
    List<course> findCListService(String sid,int start,int count);
    List<student> findSListService(String cid);
    List<student> findSListService(String cid,int start,int count);
    List<student> addSListService(String cid);
    List<student> addSListService(String cid,int start,int count);
    List<course> addCListService(String sid);
    List<course> addCListService(String sid,int start,int count);
    List<course> findCListService(String sid,String type,String var);
    List<course> findCListService(String sid,int start,int count,String type,String var);
    List<course> addCListService(String sid,String type,String var);
    List<course> addCListService(String sid,int start,int count,String type,String var);
}
