package dao.impl;

import dao.baseDao;
import dao.courseDao;
import pojo.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class courseDaoImpl extends baseDao implements courseDao {
    @Override
    public List<course> showAllCourseDao() {
        String sql="select * from course";
        List<course> lc=new ArrayList<>();
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=null;
            rs=ps.executeQuery();
            int i=0;
            while(rs.next()){
                i++;
                course c=new course();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                c.setTid(rs.getInt("tid"));
                c.setTname(rs.getString("tname"));
                c.setIid(rs.getInt("iid"));
                c.setIname(rs.getString("iname"));
                c.setCintro(rs.getString("cintro"));
                lc.add(c);
            }
            System.out.println(lc+"     "+i);
            conn.close();
            return lc;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<course> showSubCourseDao(String type, String var) {
        String sql="";
        String sql1="select * from course where cid=?";
        String sql2="select * from course where cname=?";
        String sql3="select * from course where tid=?";
        String sql4="select * from course where tname=?";
        String sql5="select * from course where iid=?";
        String sql6="select * from course where iname=?";
        if("cid".equals(type))sql=sql1;
        else if("cname".equals(type))sql=sql2;
        else if("tid".equals(type))sql=sql3;
        else if("tname".equals(type))sql=sql4;
        else if("iid".equals(type))sql=sql5;
        else if("iname".equals(type))sql=sql6;
        int id=0;

        List<course> lc=new ArrayList<>();
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            if("cid".equals(type)||"tid".equals(type)||"iid".equals(type)){
                id=Integer.parseInt(var);
                ps.setInt(1,id);
            }
            else ps.setString(1,var);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                course c=new course();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                c.setTid(rs.getInt("tid"));
                c.setTname(rs.getString("tname"));
                c.setIid(rs.getInt("iid"));
                c.setIname(rs.getString("iname"));
                c.setCintro(rs.getString("cintro"));
                lc.add(c);
            }
            conn.close();
            return lc;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<course> showSubCourseDao(String type, String var, int tid) {
        String sql="";
        String sql1="select * from course where cid=? and tid=?";
        String sql2="select * from course where cname=? and tid=?";
        String sql5="select * from course where iid=? and tid=?";
        String sql6="select * from course where iname=? and tid=?";
        if("cid".equals(type))sql=sql1;
        else if("cname".equals(type))sql=sql2;
        else if("iid".equals(type))sql=sql5;
        else if("iname".equals(type))sql=sql6;
        int id=0;
        List<course> lc=new ArrayList<>();
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            if("cid".equals(type)||"tid".equals(type)||"iid".equals(type)){
                id=Integer.parseInt(var);
                ps.setInt(1,id);
            }
            else ps.setString(1,var);
            ps.setInt(2,tid);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                course c=new course();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                c.setTid(rs.getInt("tid"));
                c.setTname(rs.getString("tname"));
                c.setIid(rs.getInt("iid"));
                c.setIname(rs.getString("iname"));
                c.setCintro(rs.getString("cintro"));
                lc.add(c);
            }
            conn.close();
            return lc;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<course> showAllCourseDao(int start, int count) {
        ArrayList<course> lc=new ArrayList<>();
        //System.out.println(start+"  "+count);
        String sql="select top "+count+" * from course where cid not in(select top "+start+" cid from course)";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                course c=new course();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                c.setTid(rs.getInt("tid"));
                c.setTname(rs.getString("tname"));
                c.setIid(rs.getInt("iid"));
                c.setIname(rs.getString("iname"));
                c.setCintro(rs.getString("cintro"));
                lc.add(c);
            }
            conn.close();
            return lc;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<course> showSubCourseDao(String type, String var, int start, int count) {
        ArrayList<course> lc=new ArrayList<>();
        int id=0;
        String sql="";
        String sql1="select top "+count+" * from course where cid=? and cid not in(select top "+start+" cid from course)";
        String sql2="select top "+count+" * from course where cname=? and cid not in(select top "+start+" cid from course)";
        String sql3="select top "+count+" * from course where tid=? and cid not in(select top "+start+" cid from course)";
        String sql4="select top "+count+" * from course where tname=? and cid not in(select top "+start+" cid from course)";
        String sql5="select top "+count+" * from course where iid=? and cid not in(select top "+start+" cid from course)";
        String sql6="select top "+count+" * from course where iname=? and cid not in(select top "+start+" cid from course)";
        if("cid".equals(type))sql=sql1;
        else if("cname".equals(type))sql=sql2;
        else if("tid".equals(type))sql=sql3;
        else if("tname".equals(type))sql=sql4;
        else if("iid".equals(type))sql=sql5;
        else if("iname".equals(type))sql=sql6;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            if("cid".equals(type)||"tid".equals(type)||"iid".equals(type)){
                id=Integer.parseInt(var);
                ps.setInt(1,id);
            }
            else ps.setString(1,var);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                course c=new course();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                c.setTid(rs.getInt("tid"));
                c.setTname(rs.getString("tname"));
                c.setIid(rs.getInt("iid"));
                c.setIname(rs.getString("iname"));
                c.setCintro(rs.getString("cintro"));
                lc.add(c);
            }
            conn.close();
            return lc;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<course> showSubCourseDao(String type, String var, int start, int count, int tid) {
        ArrayList<course> lc=new ArrayList<>();
        int id=0;
        String sql="";
        String sql1="select top "+count+" * from course where cid=? and tid=? and cid not in(select top "+start+" cid from course)";
        String sql2="select top "+count+" * from course where cname=? and tid=? and cid not in(select top "+start+" cid from course)";
        String sql5="select top "+count+" * from course where iid=? and tid=? and cid not in(select top "+start+" cid from course)";
        String sql6="select top "+count+" * from course where iname=? and tid=? and cid not in(select top "+start+" cid from course)";
        if("cid".equals(type))sql=sql1;
        else if("cname".equals(type))sql=sql2;
        else if("iid".equals(type))sql=sql5;
        else if("iname".equals(type))sql=sql6;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            if("cid".equals(type)||"tid".equals(type)||"iid".equals(type)){
                id=Integer.parseInt(var);
                ps.setInt(1,id);
            }
            else ps.setString(1,var);
            ps.setInt(2,tid);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                course c=new course();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                c.setTid(rs.getInt("tid"));
                c.setTname(rs.getString("tname"));
                c.setIid(rs.getInt("iid"));
                c.setIname(rs.getString("iname"));
                c.setCintro(rs.getString("cintro"));
                lc.add(c);
            }
            conn.close();
            return lc;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addCourseDao(course c) {
        String sql="insert into course values (?,?,?,?,?,?)";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,c.getCname());
            ps.setInt(2,c.getIid());
            ps.setString(3,c.getIname());
            ps.setInt(4,c.getTid());
            ps.setString(5,c.getTname());
            ps.setString(6,c.getCintro());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delCourseDao(String cid) {
        String sql="delete from course where cid=?";
        int c=Integer.parseInt(cid);
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,c);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean upCourseDao(course c) {
        String sql="update course set cname=?,tid=?,tname=?,iid=?,iname=?,cintro=? where cid=?";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,c.getCname());
            ps.setInt(2,c.getTid());
            ps.setString(3,c.getTname());
            ps.setInt(4,c.getIid());
            ps.setString(5,c.getIname());
            ps.setString(6,c.getCintro());
            ps.setInt(7,c.getCid());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
