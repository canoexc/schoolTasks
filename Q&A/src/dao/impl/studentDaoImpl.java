package dao.impl;

import dao.baseDao;
import dao.studentDao;
import pojo.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class studentDaoImpl extends baseDao implements studentDao {
    @Override
    public student checkStudentLoginDao(String sid, String pwd) {
        String sql="select * from student where sid=? and pwd=?";
        student sd=new student();
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=null;
            int id=Integer.parseInt(sid);
            ps.setInt(1,id);
            ps.setString(2,pwd);
            rs=ps.executeQuery();
            if(rs.next()){
                sd.setSid(rs.getInt("sid"));
                sd.setSname(rs.getString("sname"));
                sd.setPwd(rs.getString("pwd"));
            }
            else sd=null;
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return sd;
    }

    @Override
    public int addStudentDao(String sname, String pwd) {
        String sql="insert into student values (?,?) select 'sid'=SCOPE_IDENTITY()";
        //String sql1="";
        ResultSet rs=null;
        int sid=-1;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            //PreparedStatement ps1=conn.prepareStatement(sql1);
            ps.setString(1,sname);
            ps.setString(2,pwd);
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            while(rs.next())
            {
                sid=rs.getInt("sid");
                //System.out.println(sid);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("咋回事？？？");
            return 0;
        }
        return sid;
    }

    @Override
    public boolean pwdChangeDao(String sid, String pwd) {
        String sql="update student set pwd=? where sid=?";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(sid);
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
