package controller.command.implementation;

import controller.command.Command;
import model.entity.Booking;
import model.service.AdminBookingService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminGetUnpaidBookingCommand implements Command {

    private static final AdminBookingService adminBookingService = new AdminBookingService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("booking_id")).orElse("0"));

        if (id == 0) {
            request.getSession().setAttribute("bookingDoNotExists", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.bookingDoNotExists"));
            return "redirect: /exhibitions/admin";
        }

        Booking unpaidBooking = adminBookingService.findUnpaidById(id);

        if (unpaidBooking == null) {
            request.getSession().setAttribute("bookingDoNotExists", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.bookingDoNotExists"));
            return "redirect: /exhibitions/admin";
        }

        request.setAttribute("unpaidBooking", unpaidBooking);
        return "/jsp/admin/unpaid_booking.jsp";
    }

}