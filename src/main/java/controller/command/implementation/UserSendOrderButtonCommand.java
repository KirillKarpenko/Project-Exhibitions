package controller.command.implementation;

import controller.command.Command;
import model.entity.Account;
import model.entity.Booking;
import model.entity.Ticket;
import model.service.UserBookingService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ResourceBundle;

public class UserSendOrderButtonCommand implements Command {

    private static final UserBookingService userBookingService = new UserBookingService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Booking booking = new Booking();
        Account account = (Account) request.getSession().getAttribute("account");

        if (account == null)
            return "redirect: /exhibitions/index?expositionPage=1";

        List<Ticket> cart = (List<Ticket>) request.getSession().getAttribute("cart");

        if (cart.isEmpty())
            return "redirect: /exhibitions/user";

        if (userBookingService.isUnpaidBookingExists(account.getId())) {
            request.getSession().setAttribute("payPreviousBooking", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.payPreviousBooking"));
            return "redirect: /exhibitions/user/cart";
        }

        double total = Double.parseDouble(request.getParameter("total"));
        booking.setAccount(account);
        booking.setTickets(cart);
        booking.setTotal(total);
        userBookingService.create(booking);
        cart.clear();
        return "redirect: /exhibitions/user";
    }

}
