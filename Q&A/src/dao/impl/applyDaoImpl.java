package dao.impl;

import dao.applyDao;
import dao.baseDao;
import pojo.apply;
import pojo.course;
import pojo.stuandcos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class applyDaoImpl extends baseDao implements applyDao {

    @Override
    public boolean addApplyDao(apply a) {
        String sql="insert into apply values(?,?,?,?,'0')";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,a.getSid());
            ps.setInt(2,a.getCid());
            ps.setInt(3,a.getTid());
            ps.setString(4,a.getPs());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean changeStatusDao(String type, String pid) {
        String sql="update apply set status=? where pid=?";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(pid);
            ps.setString(1,type);
            ps.setInt(2,id);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<stuandcos> showApplyDao(String tid,String type) {
        String sql="";
        String sql1="select a.sid,a.sname,b.cid,b.cname,c.pid,c.ps,c.status from student a,course b,apply c where a.sid=c.sid and b.cid=c.cid and c.tid=?";
        String sql2="select a.sid,a.sname,b.cid,b.cname,c.pid,c.ps,c.status from student a,course b,apply c where a.sid=c.sid and b.cid=c.cid and c.tid=? and c.status='0'";
        if("all".equals(type))sql=sql1;
        else if("new".equals(type))sql=sql2;
        int id=Integer.parseInt(tid);
        List<stuandcos> l=new ArrayList<>();
        ResultSet rs=null;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                stuandcos sac=new stuandcos();
                sac.setPid(rs.getInt("pid"));
                sac.setSid(rs.getInt("sid"));
                sac.setSname(rs.getString("sname"));
                sac.setCid(rs.getInt("cid"));
                sac.setCname(rs.getString("cname"));
                sac.setPs(rs.getString("ps"));
                sac.setStatus(rs.getString("status"));
                l.add(sac);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return l;
    }

    @Override
    public List<stuandcos> showApplyDao(String tid,String type ,int start, int count) {
        String sql="";
        String sql1="select top "+count+" a.sid,a.sname,b.cid,b.cname,c.pid,c.ps,c.status from student a,course b,apply c where a.sid=c.sid and b.cid=c.cid and c.tid=? and c.pid not in(select top "+start+" pid from apply)";
        String sql2="select top "+count+" a.sid,a.sname,b.cid,b.cname,c.pid,c.ps,c.status from student a,course b,apply c where a.sid=c.sid and b.cid=c.cid and c.tid=? and c.status='0' and c.pid not in(select top "+start+" pid from apply)";
        if("all".equals(type))sql=sql1;
        else if("new".equals(type))sql=sql2;
        int id=Integer.parseInt(tid);
        List<stuandcos> l=new ArrayList<>();
        ResultSet rs=null;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                stuandcos sac=new stuandcos();
                sac.setPid(rs.getInt("pid"));
                sac.setSid(rs.getInt("sid"));
                sac.setSname(rs.getString("sname"));
                sac.setCid(rs.getInt("cid"));
                sac.setCname(rs.getString("cname"));
                sac.setPs(rs.getString("ps"));
                sac.setStatus(rs.getString("status"));
                l.add(sac);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return l;
    }

    @Override
    public List<course> showRappDao(String sid, String type) {
        String sql="";
        String sql1="select b.*,c.pid,c.status from student a,course b,apply c where a.sid=c.sid and b.cid=c.cid and c.sid=?";
        String sql2="select b.*,c.pid,c.status from student a,course b,apply c where a.sid=c.sid and b.cid=c.cid and c.sid=? and (c.status='1' or c.status='2')";
        if("all".equals(type))sql=sql1;
        else if("new".equals(type))sql=sql2;
        int id=Integer.parseInt(sid);
        List<course> l=new ArrayList<>();
        ResultSet rs=null;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                course c=new course();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                c.setTid(rs.getInt("tid"));
                c.setTname(rs.getString("tname"));
                c.setIid(rs.getInt("pid"));
                c.setIname(rs.getString("iname"));
                c.setCintro(rs.getString("status"));
                l.add(c);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return l;
    }

    @Override
    public List<course> showRappDao(String sid, String type, int start, int count) {
        String sql="";
        String sql1="select top "+count+" b.*,c.pid,c.status from student a,course b,apply c where a.sid=c.sid and b.cid=c.cid and c.sid=? and c.pid not in(select top "+start+" pid from apply)";
        String sql2="select top "+count+" b.*,c.pid,c.status from student a,course b,apply c where a.sid=c.sid and b.cid=c.cid and c.sid=? and (c.status='1' or c.status='2') and c.pid not in(select top "+start+" pid from apply)";
        if("all".equals(type))sql=sql1;
        else if("new".equals(type))sql=sql2;
        int id=Integer.parseInt(sid);
        List<course> l=new ArrayList<>();
        ResultSet rs=null;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                course c=new course();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                c.setTid(rs.getInt("tid"));
                c.setTname(rs.getString("tname"));
                c.setIid(rs.getInt("pid"));
                c.setIname(rs.getString("iname"));
                c.setCintro(rs.getString("status"));
                l.add(c);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return l;
    }
}
