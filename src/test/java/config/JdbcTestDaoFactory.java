package config;

import com.mysql.cj.jdbc.MysqlDataSource;
import model.dao.*;
import model.dao.implementation.*;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTestDaoFactory extends DaoFactory {

    private final MysqlDataSource dataSource = TestConnectionPool.getDataSource();

    @Override
    public AccountDao createAccountDao() {
        return new AccountDaoImpl(getConnection());
    }

    @Override
    public ExpositionDao createExpositionDao() {
        return new ExpositionDaoImpl(getConnection());
    }

    @Override
    public ShowroomDao createShowroomDao() {
        return new ShowroomDaoImpl(getConnection());
    }

    @Override
    public BookingDao createBookingDao() {
        return new BookingDaoImpl(getConnection());
    }

    private Connection getConnection() {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
