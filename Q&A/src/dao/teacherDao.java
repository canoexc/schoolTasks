package dao;

import pojo.teacher;

import java.util.ArrayList;

public interface teacherDao {
    /**
     * 根据老师用户名密码查询老师信息
     * @param tid 用户编号
     * @param pwd 密码
     * @return 返回查询到的用户信息
     */
    teacher checkTeacherLoginDao(String tid,String pwd);
    /**
     * 显示所有教师
     * @return
     */
    ArrayList<teacher> allTeacherDao();
    /**
     * 显示所有分类查找教师
     * @param type
     * @param var
     * @return
     */
    ArrayList<teacher> allSubTeacherDao(String type,String var);
    /**
     * 分页查找所有教师
     * @return
     */
    ArrayList<teacher> showTeacherDao(int start,int count);
    /**
     * 分页据分信息查找教师
     * @param type
     * @param var
     * @return
     */
    ArrayList<teacher> subShowTeacherDao(String type,String var,int start,int count);
    /**
     * 添加老师
     * @return
     */
    boolean addTeacherDao(teacher t);
    /**
     * 根据编号删除教师
     * @param tid
     * @return
     */
    boolean delTeacherDao(String tid);
    /**
     * 更新教师信息
     * @param t
     * @return
     */
    boolean upTeacherDao(teacher t);

    /**
     * 修改密码
     * @param tid
     * @param pwd
     * @return
     */
    boolean pwdChange(String tid,String pwd);
}
