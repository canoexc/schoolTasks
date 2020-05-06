package service;

import pojo.admin;

public interface adminService {
    /**
     * 校验管理员登录
     * @param aname 用户名
     * @param pwd 密码
     * @return 返回查询到的用户信息
     */
    admin checkAdminLoginService(String aname,String pwd);
    public boolean pwdChange(String aid, String pwd);
}
