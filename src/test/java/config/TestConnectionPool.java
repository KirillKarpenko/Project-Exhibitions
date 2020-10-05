package config;

import com.mysql.cj.jdbc.MysqlDataSource;
import model.dao.implementation.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnectionPool {

    private TestConnectionPool() {}

    private static MysqlDataSource dataSource;

    public static MysqlDataSource getDataSource() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("sql_test_config");
        MysqlDataSource result = dataSource;

        if (result != null)
            return result;

        synchronized (ConnectionPool.class) {
            if (dataSource == null) {
                dataSource = new MysqlDataSource();
                dataSource.setURL(resourceBundle.getString("database.url"));
                dataSource.setUser(resourceBundle.getString("database.username"));
                dataSource.setPassword(resourceBundle.getString("database.password"));
            }
        }

        return dataSource;
    }

}
