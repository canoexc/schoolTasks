package com.exp.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardDaoImpl implements CardDao {
    public int loginDao(String id, String pwd) {
        String sql="select * from card where id=? and pwd=?";
        int ok=0;
        try{
            baseDao dataSource = new baseDao();
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=null;
            ps.setString(1,id);
            ps.setString(2,pwd);
            rs=ps.executeQuery();
            if(rs.next()&&queryLockDao(id)<3){
                ok=1;
                sql="update card set state=0 where id=?";
                PreparedStatement ps1=conn.prepareStatement(sql);
                ps1.setString(1,id);
                ps1.executeUpdate();
            }
            else{
                lockDao(id);
            }
            ps.close();
            rs.close();
            dataSource.releaseConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return ok;
    }

    public int queryDao(String id) {
        String sql="select money from card where id=?";
        int ok=-9999;
        try{
            baseDao dataSource = new baseDao();
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=null;
            ps.setString(1,id);
            rs=ps.executeQuery();
            if(rs.next()){
                ok=rs.getInt("money");
            }
            ps.close();
            rs.close();
            dataSource.releaseConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    public int withdrawDao(String id, int m) {
        int now=queryDao(id);
        if(now<m)return -9999;
        else{
            String sql="update card set money=? where id=?";
            try{
                baseDao dataSource = new baseDao();
                Connection conn=dataSource.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setInt(1,now-m);
                ps.setString(2,id);
                ps.executeUpdate();
                ps.close();
                dataSource.releaseConnection(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    public int topUpDao(String id, int m) {
        int now=queryDao(id);
        String sql="update card set money=? where id=?";
        try{
            baseDao dataSource = new baseDao();
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,now+m);
            ps.setString(2,id);
            ps.executeUpdate();
            ps.close();
            dataSource.releaseConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int queryLockDao(String id) {
        String sql="select state from card where id=?";
        int ok=0;
        try{
            baseDao dataSource = new baseDao();
            Connection conn=dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=null;
            ps.setString(1,id);
            rs=ps.executeQuery();
            if(rs.next()){
                ok=rs.getInt("state");
            }
            ps.close();
            rs.close();
            dataSource.releaseConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return ok;
    }

    public int lockDao(String id) {
        int now=queryLockDao(id);
        String sql="update card set state=? where id=?";
        try{
            baseDao dataSource = new baseDao();
            Connection conn =dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,++now);
            ps.setString(2,id);
            ps.executeUpdate();
            ps.close();
            dataSource.releaseConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public int deadLock(String id) {
        String sql="update card set state=-9999 where id=?";
        try{
            baseDao dataSource = new baseDao();
            Connection conn =dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.executeUpdate();
            ps.close();
            dataSource.releaseConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public void loggerDao(String id, int m) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowtime = d.format(new Date());
        int a=queryDao(id);
        String sql="insert into logger values(?,?,?,?,?)";
        try{
            baseDao dataSource = new baseDao();
            Connection conn =dataSource.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.setInt(2,m);
            ps.setString(3,nowtime);
            ps.setInt(4,m+a);
            ps.setInt(5,a);
            ps.executeUpdate();
            ps.close();
            dataSource.releaseConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
