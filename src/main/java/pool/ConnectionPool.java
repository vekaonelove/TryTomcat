package pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
        config.setJdbcUrl("jdbc:mysql://localhost:3307/repet");
        config.setUsername("root");
        config.setPassword("12345678Ab*");
        config.setMaximumPoolSize(10);
        ds = new HikariDataSource(config);
    }

    private ConnectionPool() {}
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}