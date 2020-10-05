package config;

import model.dao.*;
import model.dao.implementation.JdbcDaoFactory;

public abstract class TestDaoFactory {

    private static DaoFactory daoFactory;

    public abstract AccountDao createAccountDao();
    public abstract ExpositionDao createExpositionDao();
    public abstract ShowroomDao createShowroomDao();
    public abstract BookingDao createBookingDao();

    public static DaoFactory getInstance() {
        DaoFactory result = daoFactory;

        if (result != null)
            return result;

        synchronized (TestDaoFactory.class) {
            if (daoFactory == null)
                daoFactory = new JdbcTestDaoFactory();

            return daoFactory;
        }

    }

}
