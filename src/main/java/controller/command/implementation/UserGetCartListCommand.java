package controller.command.implementation;

import controller.command.Command;
import model.entity.Ticket;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ResourceBundle;

public class UserGetCartListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Ticket> cart = (List<Ticket>) request.getSession().getAttribute("cart");
        BigDecimal total = new BigDecimal("0.00");

        if (cart == null || cart.isEmpty()) {
            request.getSession().setAttribute("cartIsEmpty", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.cartIsEmpty"));
            return "redirect: /exhibitions/user";
        }

        for (Ticket ticket : cart)
            total = total.add(BigDecimal.valueOf(ticket.getQuantity()).multiply(BigDecimal.valueOf(ticket.getExposition().getPrice())));

        total = total.setScale(2, RoundingMode.CEILING);
        request.getSession().setAttribute("cart", cart);
        request.setAttribute("total", total.doubleValue());
        return "/jsp/user/cart.jsp";
    }

}
