package model.service;

import model.dao.BookingDao;
import model.dao.DaoFactory;
import model.entity.Booking;

import java.util.List;

public class UserBookingService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public void create(Booking booking) {
        try (BookingDao bookingDao = daoFactory.createBookingDao()) {
            bookingDao.create(booking);
        }
    }

    public boolean isUnpaidBookingExists(int id) {
        try (BookingDao bookingDao = daoFactory.createBookingDao()) {
            Booking booking = bookingDao.findUnpaidBookingByAccountId(id);
            return booking != null;
        }
    }

    public List<Booking> findAccountBookingsByPage(int id, int start, int total) {
        try (BookingDao bookingDao = daoFactory.createBookingDao()) {
            return bookingDao.findAccountBookingsByPage(id, start, total);
        }
    }

    public int accountAmount(int id) {
        try (BookingDao bookingDao = daoFactory.createBookingDao()) {
            return bookingDao.accountAmount(id);
        }
    }

}
