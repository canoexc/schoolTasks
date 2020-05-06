package com.exp.server.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

public class baseDao {
    public DataSource dataSource;
    private LinkedList<Connection> dataSources = new LinkedList<Connection>();
    public baseDao() {
        for(int i=0;i<10;i++) {
            try {
                DriverManager.registerDriver(new SQLServerDriver());
                Connection con=DriverManager.getConnection(
                        "jdbc:sqlserver://localhost:1433;database=bankcard","Tom","123456"
                );
                dataSources.add(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() throws Exception {
        final Connection conn=dataSources.removeFirst();
        return conn;
    }
    public void releaseConnection(Connection conn){
        dataSources.add(conn);
    }
}