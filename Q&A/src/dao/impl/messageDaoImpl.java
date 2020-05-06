package dao.impl;

import dao.baseDao;
import dao.messageDao;
import pojo.message;
import pojo.uni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class messageDaoImpl extends baseDao implements messageDao {
    @Override
    public boolean addMessageDao(message m) {
        String sql="insert into message values (?,?,?,?,?,?,'0')";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowtime = d.format(new Date());
            ps.setString(1,m.getHeader());
            ps.setString(2,m.getQuestion());
            ps.setInt(3,m.getSid());
            ps.setString(4,m.getSname());
            ps.setInt(5,m.getCid());
            ps.setString(6,nowtime);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delMessageDao(String mid) {
        String sql="delete from message where mid=?";
        String sql1="delete from reply where mid=?";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            PreparedStatement ps1=conn.prepareStatement(sql1);
            int id=Integer.parseInt(mid);
            ps.setInt(1,id);
            ps1.setInt(1,id);
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
    public boolean upMessageDao(message m) {
        String sql="update message set header=?,question=? where mid=?";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,m.getHeader());
            ps.setString(2,m.getQuestion());
            ps.setInt(3,m.getMid());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<uni> showMesDao(String type, String var,String var1) {
        List<uni> l=new ArrayList<>();
        String sql="";
        String sql1="select a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=? and a.cid=b.cid and b.tid=c.tid";
        String sql2="select a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=b.cid and b.tid=c.tid and a.sid=?";
        String sql3="select a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=b.cid and b.tid=c.tid and a.cid=? and a.header like '%"+var1+"%'";
        String sql4="select a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=b.cid and b.tid=c.tid and a.cid=? and a.question like '%"+var1+"%'";
        String sql5="select a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=b.cid and b.tid=c.tid and a.header like '%"+var1+"%'";
        String sql6="select a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=b.cid and b.tid=c.tid and a.question like '%"+var1+"%'";
        String sql7="select a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=b.cid and c.tid=b.tid and a.status='0' and b.tid=?";
        String sql8="select a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=b.cid and c.tid=b.tid and b.tid=?";
        int ok=0;
        if("cid".equals(type))sql=sql1;
        else if("sid".equals(type))sql=sql2;
        else if("header".equals(type))sql=sql3;
        else if ("question".equals(type))sql=sql4;
        else if("allheader".equals(type)){sql=sql5;ok=1;}
        else if("allquestion".equals(type)){sql=sql6;ok=1;}
        else if("new".equals(type))sql=sql7;
        else if("all".equals(type))sql=sql8;
        else if("aid".equals(type)){
            sql="select a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=b.cid and b.tid=c.tid";ok=1;
        }
        ResultSet rs=null;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            if(ok==0)
            {
                int id=Integer.parseInt(var);
                ps.setInt(1,id);
            }
            rs=ps.executeQuery();
            while(rs.next()){
                uni u=new uni();
                u.setMid(rs.getInt("mid"));
                u.setCname(rs.getString("cname"));
                u.setTid(rs.getInt("tid"));
                u.setTname(rs.getString("tname"));
                u.setSid(rs.getInt("sid"));
                u.setHeader(rs.getString("header"));
                u.setSname(rs.getString("sname"));
                u.setCid(rs.getInt("cid"));
                u.setQuestion(rs.getString("question"));
                u.setDate(rs.getString("date"));
                u.setStatus(rs.getString("status"));
                l.add(u);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return l;
    }

    @Override
    public List<uni> showMesDao(String type, String var, String var1, int start, int count) {
        List<uni> l=new ArrayList<>();
        String sql="";
        String sql1="select top "+count+" a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=? and a.cid=b.cid and b.tid=c.tid and a.mid not in(select top "+start+" mid from message)";
        String sql2="select top "+count+" a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.sid=? and a.cid=b.cid and b.tid=c.tid and a.mid not in(select top "+start+" mid from message)";
        String sql3="select top "+count+" a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=? and a.cid=b.cid and b.tid=c.tid and header like '%"+var1+"%' and mid not in(select top "+start+" mid from message)";
        String sql4="select top "+count+" a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=? and a.cid=b.cid and b.tid=c.tid and question like '%"+var1+"%' and mid not in(select top "+start+" mid from message)";
        String sql5="select top "+count+" a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=? and a.cid=b.cid and b.tid=c.tid and header like '%"+var1+"%' and mid not in(select top "+start+" mid from message)";
        String sql6="select top "+count+" a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=? and a.cid=b.cid and b.tid=c.tid and question like '%"+var1+"%' and mid not in(select top "+start+" mid from message)";
        String sql7="select top "+count+" a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=b.cid and c.tid=b.tid and a.status='0' and b.tid=? and a.mid not in(select top "+start+" mid from message)";
        String sql8="select top "+count+" a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=b.cid and c.tid=b.tid and b.tid=? and a.mid not in(select top "+start+" mid from message)";
        int ok=0;
        if("cid".equals(type))sql=sql1;
        else if("sid".equals(type))sql=sql2;
        else if("header".equals(type))sql=sql3;
        else if ("question".equals(type))sql=sql4;
        else if("allheader".equals(type)){sql=sql5;ok=1;}
        else if("allquestion".equals(type)){sql=sql6;ok=1;}
        else if("new".equals(type))sql=sql7;
        else if("all".equals(type))sql=sql8;
        else if("aid".equals(type)){
            sql="select top "+count+" a.*,b.cname,c.tid,c.tname from message a,course b,teacher c where a.cid=b.cid and b.tid=c.tid and a.mid not in(select top "+start+" mid from message)";ok=1;
        }
        ResultSet rs=null;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            if(ok==0)
            {
                int id=Integer.parseInt(var);
                ps.setInt(1,id);
            }
            rs=ps.executeQuery();
            while(rs.next()){
                uni u=new uni();
                u.setMid(rs.getInt("mid"));
                u.setCname(rs.getString("cname"));
                u.setTid(rs.getInt("tid"));
                u.setTname(rs.getString("tname"));
                u.setSid(rs.getInt("sid"));
                u.setHeader(rs.getString("header"));
                u.setSname(rs.getString("sname"));
                u.setCid(rs.getInt("cid"));
                u.setQuestion(rs.getString("question"));
                u.setDate(rs.getString("date"));
                u.setStatus(rs.getString("status"));
                l.add(u);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return l;
    }
}
