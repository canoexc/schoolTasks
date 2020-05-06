package dao;

import pojo.admin;

public interface adminDao {
    /**
     * 根据管理员用户名密码查询管理员信息
     * @param aname 用户名
     * @param pwd 密码
     * @return 返回查询到的用户信息
     */
    admin checkAdminLoginDao(String aname,String pwd);
    boolean pwdChange(String tid,String pwd);
}
