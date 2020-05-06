package service;

import pojo.course;

import java.util.List;

public interface courseService {
    List<course> showAllCourseService();
    List<course> showSubCourseService(String type,String var);
    List<course> showSubCourseService(String type,String var,int tid);
    List<course> showAllCourseService(int start,int count);
    List<course> showSubCourseService(String type,String var,int start,int count);
    List<course> showSubCourseService(String type,String var,int tid,int start,int count);
    boolean addCourseService(course c);
    boolean delCourseService(String cid);
    boolean upCourseService(course c);
}
