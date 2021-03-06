package model.service;

import model.dao.BookingDao;
import model.dao.DaoFactory;
import model.entity.Booking;

public class AdminBookingService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public Booking findUnpaidById(int id) {
        try (BookingDao bookingDao = daoFactory.createBookingDao()) {
            return bookingDao.findUnpaidById(id);
        }
    }

    public void updatePaidInformationById(int id) {
        try (BookingDao bookingDao = daoFactory.createBookingDao()) {
            bookingDao.updatePaidInformationById(id);
        }
    }

    public void deleteById(int id) {
        try (BookingDao bookingDao = daoFactory.createBookingDao()) {
            bookingDao.deleteById(id);
        }
    }

}
