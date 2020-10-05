package model.dao;

import model.entity.Booking;

import java.util.List;

public interface BookingDao extends GenericDao<Booking> {
    Booking findUnpaidBookingByAccountId(int id);
    Booking findUnpaidById(int id);
    List<Booking> findAccountBookingsByPage(int id, int start, int total);
    int accountAmount(int id);
    void updatePaidInformationById(int id);
}