package dao.impl;

import dao.baseDao;
import dao.whitelistDao;
import pojo.course;
import pojo.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class whitelistDaoImpl extends baseDao implements whitelistDao {
    @Override
    public boolean addListDao(String sid, String cid) {
        String sql="insert into whitelist values (?,?,'0')";
        try{
            int ids=Integer.parseInt(sid);
            int idc=Integer.parseInt(cid);
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,ids);
            ps.setInt(2,idc);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean readedListDao(String wid) {
        String sql="update whitelist set sta='1' where wid=?";
        try{
            int idw=Integer.parseInt(wid);
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,idw);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delListDao(String sid,String cid) {
        String sql="delete from whitelist where sid=? and cid=?";
        int ids=Integer.parseInt(sid);
        int idc=Integer.parseInt(cid);
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,ids);
            ps.setInt(2,idc);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<course> findCListDao(String sid) {
        ArrayList<course> clist=new ArrayList<>();
        String sql="select a.* from course a,whitelist b where a.cid=b.cid and b.sid=?";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(sid);
            ps.setInt(1,id);
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
                clist.add(c);
            }
            conn.close();
            return clist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<course> findCListDao(String sid, int start, int count) {
        ArrayList<course> clist=new ArrayList<>();
        String sql="select top "+count+" a.* from course a,whitelist b where a.cid=b.cid and b.sid=? and a.cid not in(select top "+start+" cid from course)";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(sid);
            ps.setInt(1,id);
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
                clist.add(c);
            }
            conn.close();
            return clist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<student> findSListDao(String cid) {
        ArrayList<student> slist=new ArrayList<>();
        String sql="select a.* from student a,whitelist b where a.sid=b.sid and b.cid=?";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(cid);
            ps.setInt(1,id);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                student s=new student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setPwd(rs.getString("pwd"));
                slist.add(s);
            }
            conn.close();
            return slist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<student> findSListDao(String cid, int start, int count) {
        ArrayList<student> slist=new ArrayList<>();
        String sql="select top "+count+" a.* from student a,whitelist b where a.sid=b.sid and b.cid=? and a.sid not in(select top "+start+" sid from student)";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(cid);
            ps.setInt(1,id);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                student s=new student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setPwd(rs.getString("pwd"));
                slist.add(s);
            }
            conn.close();
            return slist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<student> addSListDao(String cid) {
        ArrayList<student> slist=new ArrayList<>();
        String sql="select a.* from student a where a.sid not in(select sid from whitelist where cid=?)";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(cid);
            ps.setInt(1,id);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                student s=new student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setPwd(rs.getString("pwd"));
                slist.add(s);
            }
            conn.close();
            return slist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<student> addSListDao(String cid, int start, int count) {
        ArrayList<student> slist=new ArrayList<>();
        String sql="select top "+count+" a.* from student a where a.sid not in(select sid from whitelist where cid=?) and a.sid not in(select top "+start+" sid from student)";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(cid);
            ps.setInt(1,id);
            ResultSet rs=null;
            rs=ps.executeQuery();
            while(rs.next()){
                student s=new student();
                s.setSid(rs.getInt("sid"));
                s.setSname(rs.getString("sname"));
                s.setPwd(rs.getString("pwd"));
                slist.add(s);
            }
            conn.close();
            return slist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<course> addCListDao(String sid) {
        ArrayList<course> clist=new ArrayList<>();
        String sql="select a.* from course a where a.cid not in(select cid from whitelist where sid=?)";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(sid);
            ps.setInt(1,id);
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
                clist.add(c);
            }
            conn.close();
            return clist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<course> addCListDao(String sid, int start, int count) {
        ArrayList<course> clist=new ArrayList<>();
        String sql="select top "+count+" a.* from course a where a.cid not in(select cid from whitelist where sid=?) and a.cid not in(select top "+start+" cid from course)";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(sid);
            ps.setInt(1,id);
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
                clist.add(c);
            }
            conn.close();
            return clist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<course> findCListDao(String sid, String type, String var) {
        ArrayList<course> clist=new ArrayList<>();
        String sql="select a.* from course a,whitelist b where a.cid=b.cid and b.sid=? ";
        String plus1="and a.cid=?";
        String plus2="and a.cname=?";
        String plus3="and a.tid=?";
        String plus4="and a.tname=?";
        String plus5="and a.iid=?";
        String plus6="and a.iname=?";
        if("cid".equals(type))sql=sql+plus1;
        else if("cname".equals(type))sql=sql+plus2;
        else if("tid".equals(type))sql=sql+plus3;
        else if("tname".equals(type))sql=sql+plus4;
        else if("iid".equals(type))sql=sql+plus5;
        else if("iname".equals(type))sql=sql+plus6;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(sid);
            ps.setInt(1,id);
            if("cid".equals(type)||"sid".equals(type)||"iid".equals(type)){
                int id1=Integer.parseInt(var);
                ps.setInt(2,id1);
            }else ps.setString(2,var);
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
                clist.add(c);
            }
            conn.close();
            return clist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<course> findCListDao(String sid, int start, int count, String type, String var) {
        ArrayList<course> clist=new ArrayList<>();
        String sql="select top "+count+" a.* from course a,whitelist b where a.cid=b.cid and b.sid=? and a.cid not in(select top "+start+" cid from course) ";
        String plus1="and a.cid=?";
        String plus2="and a.cname=?";
        String plus3="and a.tid=?";
        String plus4="and a.tname=?";
        String plus5="and a.iid=?";
        String plus6="and a.iname=?";
        if("cid".equals(type))sql=sql+plus1;
        else if("cname".equals(type))sql=sql+plus2;
        else if("tid".equals(type))sql=sql+plus3;
        else if("tname".equals(type))sql=sql+plus4;
        else if("iid".equals(type))sql=sql+plus5;
        else if("iname".equals(type))sql=sql+plus6;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(sid);
            ps.setInt(1,id);
            if("cid".equals(type)||"sid".equals(type)||"iid".equals(type)){
                int id1=Integer.parseInt(var);
                ps.setInt(2,id1);
            }else ps.setString(2,var);
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
                clist.add(c);
            }
            conn.close();
            return clist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<course> addCListDao(String sid, String type, String var) {
        ArrayList<course> clist=new ArrayList<>();
        String sql="select a.* from course a where a.cid not in(select cid from whitelist where sid=?) ";
        String plus1="and a.cid=?";
        String plus2="and a.cname=?";
        String plus3="and a.tid=?";
        String plus4="and a.tname=?";
        String plus5="and a.iid=?";
        String plus6="and a.iname=?";
        if("cid".equals(type))sql=sql+plus1;
        else if("cname".equals(type))sql=sql+plus2;
        else if("tid".equals(type))sql=sql+plus3;
        else if("tname".equals(type))sql=sql+plus4;
        else if("iid".equals(type))sql=sql+plus5;
        else if("iname".equals(type))sql=sql+plus6;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(sid);
            ps.setInt(1,id);
            if("cid".equals(type)||"sid".equals(type)||"iid".equals(type)){
                int id1=Integer.parseInt(var);
                ps.setInt(2,id1);
            }else ps.setString(2,var);
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
                clist.add(c);
            }
            conn.close();
            return clist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<course> addCListDao(String sid, int start, int count, String type, String var) {
        ArrayList<course> clist=new ArrayList<>();
        String sql="select top "+count+" a.* from course a where a.cid not in(select cid from whitelist where sid=?) and a.cid not in(select top "+start+" cid from course) ";
        String plus1="and a.cid=?";
        String plus2="and a.cname=?";
        String plus3="and a.tid=?";
        String plus4="and a.tname=?";
        String plus5="and a.iid=?";
        String plus6="and a.iname=?";
        if("cid".equals(type))sql=sql+plus1;
        else if("cname".equals(type))sql=sql+plus2;
        else if("tid".equals(type))sql=sql+plus3;
        else if("tname".equals(type))sql=sql+plus4;
        else if("iid".equals(type))sql=sql+plus5;
        else if("iname".equals(type))sql=sql+plus6;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id=Integer.parseInt(sid);
            ps.setInt(1,id);
            if("cid".equals(type)||"sid".equals(type)||"iid".equals(type)){
                int id1=Integer.parseInt(var);
                ps.setInt(2,id1);
            }else ps.setString(2,var);
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
                clist.add(c);
            }
            conn.close();
            return clist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
