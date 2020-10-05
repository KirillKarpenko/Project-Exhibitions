package controller.command.implementation;

import controller.command.Command;
import model.service.AdminExpositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCreateShowroomPageCommand implements Command {

    AdminExpositionService adminExpositionService = new AdminExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<String> expositionsNames = adminExpositionService.findExpositionsNames();
        request.setAttribute("expositionsNames", expositionsNames);
        return "/jsp/admin/create_showroom.jsp";
    }

}
