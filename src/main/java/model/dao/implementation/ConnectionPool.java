package model.dao.implementation;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.util.ResourceBundle;

public class ConnectionPool {

    private ConnectionPool() {}

    private static MysqlDataSource dataSource;

    public static MysqlDataSource getDataSource() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("sql_config");
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
