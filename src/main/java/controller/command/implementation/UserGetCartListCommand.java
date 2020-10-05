package controller.command.implementation;

import controller.command.Command;
import model.entity.Ticket;
import util.LocaleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class UserGetCartListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Ticket> cart = (List<Ticket>) request.getSession().getAttribute("cart");
        BigDecimal total = new BigDecimal("0.00");

        if (cart == null || cart.isEmpty()) {
            request.getSession().setAttribute("cartIsEmpty", LocaleManager.getString("error.cartIsEmpty"));
            return "/jsp/user/user.jsp";
        }

        for (Ticket ticket : cart)
            total = total.add(BigDecimal.valueOf(ticket.getQuantity()).multiply(BigDecimal.valueOf(ticket.getExposition().getPrice())));

        total = total.setScale(2, RoundingMode.CEILING);
        request.getSession().setAttribute("cart", cart);
        request.setAttribute("total", total.doubleValue());
        return "/jsp/user/cart.jsp";
    }

}
