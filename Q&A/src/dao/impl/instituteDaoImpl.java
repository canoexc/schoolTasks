package dao.impl;

import dao.baseDao;
import dao.instituteDao;
import pojo.institute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class instituteDaoImpl extends baseDao implements instituteDao {
    @Override
    public List<institute> showAllInstituteDao() {
        String sql="select * from institute";
        List<institute> li=new ArrayList<>();
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                institute i=new institute();
                i.setIid(rs.getInt("iid"));
                i.setIname(rs.getString("iname"));
                i.setIintro(rs.getString("iintro"));
                li.add(i);
            }
            conn.close();
            return li;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<institute> showSubInstituteDao(String type, String var) {
        String sql="";
        String sql1="select * from institute where iid=?";
        String sql2="select * from institute where iname=?";
        int id=0;
        if("iid".equals(type))sql=sql1;
        else if("iname".equals(type))sql=sql2;
        List<institute> li=new ArrayList<>();
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            if("iid".equals(type)){
                id=Integer.parseInt(var);
                ps.setInt(1,id);
            }
            else ps.setString(1,var);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                institute i=new institute();
                i.setIid(rs.getInt("iid"));
                i.setIname(rs.getString("iname"));
                i.setIintro(rs.getString("iintro"));
                li.add(i);
            }
            conn.close();
            return li;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<institute> showAllInstituteDao(int start, int count) {
        ArrayList<institute> li=new ArrayList<>();
        //System.out.println(start+"  "+count);
        String sql="select top "+count+" * from institute where iid not in(select top "+start+" iid from institute)";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                institute i=new institute();
                i.setIid(rs.getInt("iid"));
                i.setIname(rs.getString("iname"));
                i.setIintro(rs.getString("iintro"));
                li.add(i);
            }
            conn.close();
            return li;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<institute> showSubInstituteDao(String type, String var, int start, int count) {
        ArrayList<institute> li=new ArrayList<>();
        int id=0;
        String sql="";
        String sql1="select top "+count+" * from institute where iid=? and iid not in(select top "+start+" iid from institute)";
        String sql2="select top "+count+" * from institute where iname=? and iid not in(select top "+start+" iid from institute)";
        if("iid".equals(type))sql=sql1;
        else if("iname".equals(type))sql=sql2;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            if("iid".equals(type)){
                id=Integer.parseInt(var);
                ps.setInt(1,id);
            }
            else ps.setString(1,var);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                institute i=new institute();
                i.setIid(rs.getInt("iid"));
                i.setIname(rs.getString("iname"));
                i.setIintro(rs.getString("iintro"));
                li.add(i);
            }
            conn.close();
            return li;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addInstituteDao(institute i) {
        String sql="insert into institute values (?,?)";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,i.getIname());
            ps.setString(2,i.getIintro());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delInstituteDao(String iid) {
        String sql="delete from institute where iid=?";
        String sql1="delete from course where iid=?";
        int i=Integer.parseInt(iid);
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps.setInt(1,i);
            ps1.setInt(1,i);
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
    public boolean upInstituteDao(institute i) {
        String sql="update institute set iname=?,iintro=? where iid=?";
        String sql1="update course set iname=? where iid=?";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            PreparedStatement ps1=conn.prepareStatement(sql1);
            ps.setInt(3,i.getIid());
            ps.setString(1,i.getIname());
            ps.setString(2,i.getIintro());
            ps1.setString(1,i.getIname());
            ps1.setInt(2,i.getIid());
            ps.executeUpdate();
            ps1.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
