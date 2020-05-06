package dao;

import pojo.course;

import java.util.List;

public interface courseDao {
    List<course> showAllCourseDao();
    List<course> showSubCourseDao(String type,String var);
    List<course> showSubCourseDao(String type,String var,int tid);
    List<course> showAllCourseDao(int start,int count);
    List<course> showSubCourseDao(String type,String var,int start,int count);
    List<course> showSubCourseDao(String type,String var,int start,int count,int tid);
    boolean addCourseDao(course c);
    boolean delCourseDao(String cid);
    boolean upCourseDao(course c);
}
