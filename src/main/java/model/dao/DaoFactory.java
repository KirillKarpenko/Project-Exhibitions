package model.dao;

import model.dao.implementation.JdbcDaoFactory;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract AccountDao createAccountDao();
    public abstract ExpositionDao createExpositionDao();
    public abstract ShowroomDao createShowroomDao();
    public abstract BookingDao createBookingDao();

    public static DaoFactory getInstance() {
        DaoFactory result = daoFactory;

        if (result != null)
            return result;

        synchronized (DaoFactory.class) {
            if (daoFactory == null)
                daoFactory = new JdbcDaoFactory();

            return daoFactory;
        }

    }

}
