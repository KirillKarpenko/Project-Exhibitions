package controller.command.implementation;

import controller.command.Command;
import model.entity.Account;
import model.entity.Booking;
import model.service.UserBookingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class UserGetAccountBookingListCommand implements Command {

    private static final UserBookingService userBookingService = new UserBookingService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Account account = (Account) request.getSession().getAttribute("account");

        if (account == null)
            return "redirect: /exhibitions/index?expositionPage=1";

        int id = account.getId();
        int total = userBookingService.accountAmount(id);
        int bookingsPerPage = 5;
        int totalAccountBookingPages = (int) Math.ceil((float) total/bookingsPerPage);
        request.setAttribute("totalAccountBookingPages", totalAccountBookingPages);
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("bookingPage")).orElse("1"));
        int start = (page-1)*bookingsPerPage;
        List<Booking> bookings = userBookingService.findAccountBookingsByPage(id, start, bookingsPerPage);
        request.setAttribute("bookings", bookings);
        request.setAttribute("bookingPage", page);
        return "/jsp/user/bookings.jsp";
    }

}
