package service;

import pojo.student;

public interface studentService {
    student checkStudentLoginService(String sid, String pwd);
    int addStudentService(String sname,String pwd);
    boolean pwdChange(String sid, String pwd);
}
