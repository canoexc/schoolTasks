package dao;

        import javax.naming.Context;
        import javax.naming.InitialContext;
        import javax.naming.NamingException;
        import javax.sql.DataSource;
        import java.sql.Connection;

public class baseDao {
    public DataSource dataSource;
    public baseDao(){
        try{
            Context context=new InitialContext();
            dataSource=(DataSource)context.lookup("java:comp/env/jdbc/sampleDS");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws Exception{
        return dataSource.getConnection();
    }
}
