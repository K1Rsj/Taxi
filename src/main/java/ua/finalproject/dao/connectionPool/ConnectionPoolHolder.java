package ua.finalproject.dao.connectionPool;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolHolder {

    private static volatile DataSource jdbcConnectionPool;

    private static DataSource getConnectionPool() throws ClassNotFoundException {
        if(jdbcConnectionPool == null){
            synchronized (ConnectionPoolHolder.class){
                if(jdbcConnectionPool == null){
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/taxi?verifyServerCertificate=false&useSSL=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                    ds.setUsername("root");
                    ds.setPassword("root");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    jdbcConnectionPool = ds;
                }
            }
        }
        return jdbcConnectionPool;
    }

    public static Connection getConnection(){
        try {
            return getConnectionPool().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();

        }
    }
}
