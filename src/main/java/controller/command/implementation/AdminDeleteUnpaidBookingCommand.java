package controller.command.implementation;

import controller.command.Command;
import model.service.AdminBookingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AdminDeleteUnpaidBookingCommand implements Command {

    AdminBookingService adminBookingService = new AdminBookingService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("booking_id")).orElse("0"));

        if (id != 0)
            adminBookingService.deleteById(id);

        return "redirect: /exhibitions/admin";
    }

}
