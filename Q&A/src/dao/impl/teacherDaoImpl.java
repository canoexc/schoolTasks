package dao.impl;

import dao.baseDao;
import dao.teacherDao;
import pojo.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class teacherDaoImpl extends baseDao implements teacherDao {
    @Override
    public teacher checkTeacherLoginDao(String tid, String pwd) {
        String sql="select * from teacher where tid=? and pwd=?";
        teacher td=new teacher();
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=null;
            int id=Integer.parseInt(tid);
            ps.setInt(1,id);
            ps.setString(2,pwd);
            rs=ps.executeQuery();
            if(rs.next()){
                td.setTid(rs.getInt("tid"));
                td.setTname(rs.getString("tname"));
                td.setPwd(rs.getString("pwd"));
                td.setTitle(rs.getString("title"));
                td.setIntro(rs.getString("intro"));
            }
            else td=null;
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return td;
    }
    public ArrayList<teacher> allTeacherDao(){
        ArrayList<teacher> tlist=new ArrayList<>();
        String sql="select * from teacher";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                teacher t=new teacher();
                t.setTid(rs.getInt("tid"));
                t.setTname(rs.getString("tname"));
                t.setPwd(rs.getString("pwd"));
                t.setTitle(rs.getString("title"));
                t.setIntro(rs.getString("intro"));
                tlist.add(t);
            }
            conn.close();
            return tlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<teacher> allSubTeacherDao(String type, String var) {
        ArrayList<teacher> tlist=new ArrayList<>();
        String sql="";
        String sql1="select * from teacher where tid=? ";
        String sql2="select * from teacher where tname=? ";
        String sql3="select * from teacher where title=? ";
        if("tid".equals(type))
            sql=sql1;
        else if("tname".equals(type))
            sql=sql2;
        else if("title".equals(type))
            sql=sql3;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            if("tid".equals(type))
            {
                int id=Integer.parseInt(var);
                ps.setInt(1,id);
            }
            else ps.setString(1,var);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                teacher t=new teacher();
                t.setTid(rs.getInt("tid"));
                t.setTname(rs.getString("tname"));
                t.setPwd(rs.getString("pwd"));
                t.setTitle(rs.getString("title"));
                t.setIntro(rs.getString("intro"));
                tlist.add(t);
            }
            conn.close();
            return tlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<teacher> showTeacherDao(int start,int count){
        ArrayList<teacher> tlist=new ArrayList<>();
        System.out.println(start+"  "+count);
        String sql="select top "+count+" * from teacher where tid not in(select top "+start+" tid from teacher)";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            //ps.setInt(1,cs);
            //ps.setInt(2,start);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                teacher t=new teacher();
                t.setTid(rs.getInt("tid"));
                t.setTname(rs.getString("tname"));
                t.setPwd(rs.getString("pwd"));
                t.setTitle(rs.getString("title"));
                t.setIntro(rs.getString("intro"));
                tlist.add(t);
            }
            conn.close();
            return tlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<teacher> subShowTeacherDao(String type, String var,int start,int count) {
        ArrayList<teacher> tlist=new ArrayList<>();
        String sql="";
        String sql1="select top "+count+" * from teacher where tid=? and tid not in(select top "+start+" tid from teacher)";
        String sql2="select top "+count+" * from teacher where tname=? and tid not in(select top "+start+" tid from teacher)";
        String sql3="select top "+count+" * from teacher where title=? and tid not in(select top "+start+" tid from teacher)";
        if("tid".equals(type))
            sql=sql1;
        else if("tname".equals(type))
            sql=sql2;
        else if("title".equals(type))
            sql=sql3;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            if("tid".equals(type))
            {
                int id=Integer.parseInt(var);
                ps.setInt(1,id);
            }
            else ps.setString(1,var);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                teacher t=new teacher();
                t.setTid(rs.getInt("tid"));
                t.setTname(rs.getString("tname"));
                t.setPwd(rs.getString("pwd"));
                t.setTitle(rs.getString("title"));
                t.setIntro(rs.getString("intro"));
                tlist.add(t);
            }
            conn.close();
            return tlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addTeacherDao(teacher t) {
        String sql="insert into teacher values (?,?,?,?)";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,t.getTname());
            ps.setString(2,t.getPwd());
            ps.setString(3,t.getTitle());
            ps.setString(4,t.getIntro());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delTeacherDao(String tid) {
        String sql="delete from teacher where tid=?";
        String sql1="delete from course where tid=?";
        int t=Integer.parseInt(tid);
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps.setInt(1,t);
            ps1.setInt(1,t);
            ps.executeUpdate();
            ps1.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean upTeacherDao(teacher t) {
        String sql="update teacher set tname=?,pwd=?,title=?,intro=? where tid=?";
        String sql1="update course set tname=? where tid=? ";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps.setString(1,t.getTname());
            ps.setString(2,t.getPwd());
            ps.setString(3,t.getTitle());
            ps.setString(4,t.getIntro());
            ps.setInt(5,t.getTid());
            ps1.setString(1,t.getTname());
            ps1.setInt(2,t.getTid());
            ps.executeUpdate();
            ps1.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean pwdChange(String tid, String pwd) {
        String sql="update teacher set pwd=? where tid=?";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(tid);
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
