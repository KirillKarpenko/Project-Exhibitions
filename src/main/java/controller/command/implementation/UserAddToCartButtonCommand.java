package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.entity.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UserAddToCartButtonCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Ticket> cart = (List<Ticket>) session.getAttribute("cart");
        Ticket newTicket = createTicket(request);

        if (newTicket == null)
            return "redirect: /exhibitions/index?expositionPage=1";

        else if (cart == null) {
            cart = new ArrayList<>();
            cart.add(newTicket);
        }

        else {
            boolean match = false;

            for (int i = 0; i < cart.size(); ++i) {
                Ticket ticket = cart.get(i);

                if (ticket.getExposition().getId() == newTicket.getExposition().getId()) {
                    ticket.setQuantity(ticket.getQuantity() + newTicket.getQuantity());
                    cart.set(i, ticket);
                    match = true;
                    break;
                }
            }

            if (!match)
                cart.add(newTicket);
        }

        session.setAttribute("cart", cart);
        return "redirect: /exhibitions/index?expositionPage=1";
    }

    private Ticket createTicket(HttpServletRequest request) {
        Ticket ticket = new Ticket();
        Exposition exposition = new Exposition();
        String expositionToString = request.getParameter("exposition");

        if (expositionToString == null || expositionToString.isEmpty())
            return null;

        ticket.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        StringTokenizer stringTokenizer = new StringTokenizer(expositionToString, ",");
        exposition.setId(Integer.parseInt(stringTokenizer.nextToken()));
        exposition.setName(stringTokenizer.nextToken());
        exposition.setCategory(Exposition.Category.valueOf(stringTokenizer.nextToken()));
        exposition.setStartDate(Timestamp.valueOf(stringTokenizer.nextToken()));
        exposition.setEndDate(Timestamp.valueOf(stringTokenizer.nextToken()));
        exposition.setPrice(Double.parseDouble(stringTokenizer.nextToken()));
        ticket.setExposition(exposition);
        return ticket;
    }

}
