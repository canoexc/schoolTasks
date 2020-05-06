package service;

import pojo.teacher;

import java.util.List;

public interface teacherService {
    /**
     * 校验教师登录
     * @param tid 用户编号
     * @param pwd 密码
     * @return
     */
    teacher checkTeacherLoginService(String tid,String pwd);

    /**
     * 获取所有教师信息
     * @return
     */
    List<teacher> allTeacherService();
    /**
     * 根据分信息获取教师信息
     * @param type
     * @param var
     * @return
     */
    List<teacher> allSubTeacherService(String type,String var);
    /**
     * 获取所有教师信息/分页
     * @return
     */
    List<teacher> showTeacherService(int start,int count);

    /**
     * 根据分信息查找教师/分页
     * @param type
     * @param var
     * @return
     */
    List<teacher> subshowTeacherService(String type,String var,int start,int count);
    /**
     * 添加新教师
     * @param t 教师信息
     * @return
     */
    boolean addTeacherService(teacher t);

    /**
     * 删除教师
     * @param tid 教师id
     * @return
     */
    boolean delTeacherService(String tid);

    /**
     * 更新教师信息
     * @param t
     * @return
     */
    boolean upTeacherService(teacher t);

    /**
     * 修改密码
     * @param tid
     * @param pwd
     * @return
     */
    boolean pwdChange(String tid,String pwd);
}
