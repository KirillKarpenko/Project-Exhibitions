package model.service;

import model.dao.BookingDao;
import model.dao.DaoFactory;
import model.entity.Booking;

import java.util.List;

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

}
