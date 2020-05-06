package dao.impl;

import dao.adminDao;
import dao.baseDao;
import pojo.admin;
import pojo.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class adminDaoImpl  extends baseDao implements adminDao {
    //@Override
    public admin checkAdminLoginDao(String aid, String pwd) {
        String sql="select * from admin where aid=? and pwd=?";
        admin ad=new admin();
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=null;
            ps.setInt(1,Integer.parseInt(aid));
            ps.setString(2,pwd);
            rs=ps.executeQuery();
            if(rs.next()){
                ad.setAid(rs.getInt("aid"));
                ad.setAname(rs.getString("aname"));
                ad.setPwd(rs.getString("pwd"));
            }
            else ad=null;
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ad;
    }

    @Override
    public boolean pwdChange(String aid, String pwd) {
        String sql="update admin set pwd=? where aid=?";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(aid);
            ps.setString(1,pwd);
            ps.setInt(2,id);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
