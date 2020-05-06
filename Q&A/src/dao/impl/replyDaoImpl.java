package dao.impl;

import dao.baseDao;
import dao.replyDao;
import pojo.message;
import pojo.reply;
import pojo.uni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class replyDaoImpl extends baseDao implements replyDao {
    @Override
    public boolean addReplyDao(reply r) {
        String sql="insert into reply values (?,?,?,?,?,'0')";
        String status=null;
        String sql1="select status from message where mid=?";
        String sql2="update message set status=? where mid=?";
        ResultSet rs=null;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            PreparedStatement ps1=conn.prepareStatement(sql1);
            PreparedStatement ps2=conn.prepareStatement(sql2);
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowtime = d.format(new Date());
            ps.setString(1,r.getAnswer());
            ps.setInt(2,r.getTid());
            ps.setString(3,r.getTname());
            ps.setInt(4,r.getMid());
            ps.setString(5,nowtime);
            ps1.setInt(1,r.getMid());
            ps.executeUpdate();
            rs=ps1.executeQuery();
            if(rs.next())status=rs.getString("status");
            status=Integer.toString(1+Integer.parseInt(status));
            ps2.setString(1,status);
            ps2.setInt(2,r.getMid());
            ps2.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delReplyDao(String rid,String mid) {
        String sql="delete from reply where rid=?";
        String status=null;
        String sql1="select status from message where mid=?";
        String sql2="update message set status=? where mid=?";
        ResultSet rs=null;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            PreparedStatement ps1=conn.prepareStatement(sql1);
            PreparedStatement ps2=conn.prepareStatement(sql2);
            ps.setInt(1,Integer.parseInt(rid));
            ps1.setInt(1,Integer.parseInt(mid));
            rs=ps1.executeQuery();
            if(rs.next()) {
                status = rs.getString("status");
                System.out.println("status:"+status);
            }
            status=Integer.toString(Integer.parseInt(status)-1<0?0:Integer.parseInt(status)-1);
            ps.executeUpdate();
            ps2.setString(1,status);
            ps2.setInt(2,Integer.parseInt(mid));
            ps2.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean upReplyDao(reply r) {
        String sql="update reply set answer=? where rid=?";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,r.getAnswer());
            ps.setInt(2,r.getRid());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean readed(String rid) {
        String sql="update reply set status='1' where rid=?";
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(rid));
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<uni> showReplyDao(String type, String var) {
        List<uni> l=new ArrayList<>();
        String sql="";
        String sql1="select b.rid,a.cname,a.cid,c.header,b.tid,b.tname,b.answer,b.date,b.status,c.question,b.mid from course a,reply b,message c where a.cid=c.cid and b.mid=c.mid and c.sid=?";
        String sql2="select b.rid,a.cname,a.cid,c.header,b.tid,b.tname,b.answer,b.date,b.status,c.question,b.mid from course a,reply b,message c where a.cid=c.cid and b.mid=c.mid and c.sid=? and b.status='0'";
        String sql3="select b.rid,a.cname,a.cid,c.header,b.tid,b.tname,b.answer,b.date,b.status,c.question,b.mid from course a,reply b,message c where a.cid=c.cid and b.mid=c.mid and b.tid=?";
        String sql4="select b.rid,a.cname,a.cid,c.header,b.tid,b.tname,b.answer,b.date,b.status,c.question,b.mid from course a,reply b,message c where a.cid=c.cid and b.mid=c.mid and b.mid=?";
        String sql5="select b.rid,a.cname,a.cid,c.header,b.tid,b.tname,b.answer,b.date,b.status,c.question,b.mid from course a,reply b,message c where a.cid=c.cid and b.mid=c.mid and b.mid=? and b.answer like '%"+var+"%'";
        if("all".equals(type))sql=sql1;
        else if("new".equals(type))sql=sql2;
        else if("tid".equals(type))sql=sql3;
        else if("show".equals(type))sql=sql4;
        else if("aid".equals(type))sql="select b.rid,a.cname,a.cid,c.header,b.tid,b.tname,b.answer,b.date,b.status,c.question,b.mid from course a,reply b,message c where a.cid=c.cid and b.mid=c.mid";
        else {
            sql = sql5;
            var=type;
        }
        ResultSet rs=null;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id;
            if(!"aid".equals(type)) {
                id = Integer.parseInt(var);
                ps.setInt(1, id);
            }
            rs=ps.executeQuery();
            while(rs.next()){
                uni u=new uni();
                u.setRid(rs.getInt("rid"));
                u.setCid(rs.getInt("cid"));
                u.setCname(rs.getString("cname"));
                u.setTid(rs.getInt("tid"));
                u.setTname(rs.getString("tname"));
                u.setHeader(rs.getString("header"));
                u.setQuestion(rs.getString("question"));
                u.setAnswer(rs.getString("answer"));
                u.setDate(rs.getString("date"));
                u.setStatus(rs.getString("status"));
                u.setMid(rs.getInt("mid"));
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
    public List<uni> showReplyDao(String type, String var, int start, int count) {
        List<uni> l=new ArrayList<>();
        String sql="";
        String sql1="select top "+count+" b.rid,a.cid,a.cname,c.header,b.tid,b.tname,b.answer,b.date,b.status,c.question,b.mid from course a,reply b,message c where a.cid=c.cid and b.mid=c.mid and c.sid=? and b.rid not in(select top "+start+" rid from reply)";
        String sql2="select top "+count+" b.rid,a.cid,a.cname,c.header,b.tid,b.tname,b.answer,b.date,b.status,c.question,b.mid from course a,reply b,message c where a.cid=c.cid and b.mid=c.mid and c.sid=? and b.status='0' and b.rid not in(select top "+start+" rid from reply)";
        String sql3="select top "+count+" b.rid,a.cid,a.cname,c.header,b.tid,b.tname,b.answer,b.date,b.status,c.question,b.mid from course a,reply b,message c where a.cid=c.cid and b.mid=c.mid and b.tid=? and b.rid not in(select top "+start+" rid from reply)";
        String sql4="select top "+count+" b.rid,a.cid,a.cname,c.header,b.tid,b.tname,b.answer,b.date,b.status,c.question,b.mid from course a,reply b,message c where a.mid=b.mid and a.mid=? and b.rid not in(select top "+start+" rid from reply)";
        String sql5="select top "+count+" b.rid,a.cid,a.cname,c.header,b.tid,b.tname,b.answer,b.date,b.status,c.question,b.mid from course a,reply b,message c where a.mid=b.mid and a.mid=? and b.answer like '%"+var+"%' and b.rid not in(select top "+start+" rid from reply)";
        if("all".equals(type))sql=sql1;
        else if("new".equals(type))sql=sql2;
        else if("tid".equals(type))sql=sql3;
        else if("show".equals(type))sql=sql4;
        else if("aid".equals(type))sql="select top "+count+" b.rid,a.cid,a.cname,c.header,b.tid,b.tname,b.answer,b.date,b.status,c.question,b.mid from course a,reply b,message c where a.cid=c.cid and b.mid=c.mid and b.rid not in(select top "+start+" rid from reply)";
        else{
            sql=sql5;
            var=type;
        }
        ResultSet rs=null;
        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            int id;
            if(!"aid".equals(type))
            {id=Integer.parseInt(var);
            ps.setInt(1,id);}
            rs=ps.executeQuery();
            while(rs.next()){
                uni u=new uni();
                u.setRid(rs.getInt("rid"));
                u.setCid(rs.getInt("cid"));
                u.setCname(rs.getString("cname"));
                u.setTid(rs.getInt("tid"));
                u.setTname(rs.getString("tname"));
                u.setHeader(rs.getString("header"));
                u.setQuestion(rs.getString("question"));
                u.setAnswer(rs.getString("answer"));
                u.setDate(rs.getString("date"));
                u.setStatus(rs.getString("status"));
                u.setMid(rs.getInt("mid"));
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
