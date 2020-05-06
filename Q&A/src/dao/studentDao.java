package dao;

import pojo.student;

public interface studentDao {
    /**
     * 根据学生用户名密码查询学生信息
     * @param sid 用户名
     * @param pwd 密码
     * @return 返回查询到的用户信息
     */
    student checkStudentLoginDao(String sid,String pwd);
    int addStudentDao(String sname,String pwd);
    boolean pwdChangeDao(String sid, String pwd);
}
