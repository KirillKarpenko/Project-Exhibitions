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
import java.util.ResourceBundle;

public class UserGetUnpaidBookingCommand implements Command {

    UserBookingService userBookingService = new UserBookingService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Account account = (Account) request.getSession().getAttribute("account");

        if (account == null)
            return "redirect: /exhibitions/index?expositionPage=1";

        int id = account.getId();
        Booking booking = userBookingService.findUnpaidBookingByAccountId(id);

        if (booking == null) {
            request.getSession().setAttribute("unpaidBookingDoNotExists", ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getString("error.unpaidBookingDoNotExists"));
            return "redirect: /exhibitions/user";
        }

        List<String> cats = new ArrayList<>();

        for (Ticket ticket : booking.getTickets())
            cats.add(ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getString("enum." + ticket.getExposition().getCategory().name().toLowerCase()));

        request.setAttribute("unpaidBooking", booking);
        request.setAttribute("categories", cats);
        return "/jsp/user/unpaid_booking.jsp";
    }

}
