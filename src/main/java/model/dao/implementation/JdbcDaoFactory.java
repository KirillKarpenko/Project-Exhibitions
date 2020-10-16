package model.dao.implementation;

//import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import model.dao.*;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private final MysqlDataSource dataSource = ConnectionPool.getDataSource();

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
