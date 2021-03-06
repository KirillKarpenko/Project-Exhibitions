package controller.command.implementation;

import controller.command.Command;
import model.entity.Account;
import model.entity.Booking;
import model.entity.Ticket;
import model.service.UserBookingService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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

        List<String> cats = new ArrayList<>();

        for (Booking booking : bookings)
            for (Ticket ticket : booking.getTickets())
                cats.add(ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getString("enum." + ticket.getExposition().getCategory().name().toLowerCase()));

        request.setAttribute("bookings", bookings);
        request.setAttribute("bookingPage", page);
        request.setAttribute("categories", cats);
        return "/jsp/user/bookings.jsp";
    }

}
