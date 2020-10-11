package controller.command.implementation;

import controller.command.Command;
import model.service.AdminShowroomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AdminDeleteShowroomButtonCommand implements Command {

    private static final AdminShowroomService adminShowroomService = new AdminShowroomService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("showroom_id")).orElse("0"));

        if (id != 0)
            adminShowroomService.deleteById(id);

        return "redirect: /exhibitions/admin/showrooms?showroomPage=1";
    }

}
