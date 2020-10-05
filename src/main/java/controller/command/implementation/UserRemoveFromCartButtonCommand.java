package controller.command.implementation;

import controller.command.Command;
import model.entity.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class UserRemoveFromCartButtonCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Ticket> cart = (List<Ticket>) request.getSession().getAttribute("cart");
        int deletionIndex = Integer.parseInt(Optional.ofNullable(request.getParameter("deletion_index")).orElse("0"));

        if (cart.get(deletionIndex) != null) {
            cart.remove(deletionIndex);

            if (cart.isEmpty())
                return "redirect: /exhibitions/user";

            request.getSession().setAttribute("cart", cart);
        }

        return "redirect: /exhibitions/user/cart";
    }

}
