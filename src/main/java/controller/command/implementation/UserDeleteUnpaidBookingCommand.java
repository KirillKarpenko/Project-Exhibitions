package controller.command.implementation;

import controller.command.Command;
import model.service.UserBookingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class UserDeleteUnpaidBookingCommand implements Command {

    UserBookingService userBookingService = new UserBookingService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("booking_id")).orElse("0"));

        if (id != 0)
            userBookingService.deleteById(id);

        return "redirect: /exhibitions/user";
    }

}
