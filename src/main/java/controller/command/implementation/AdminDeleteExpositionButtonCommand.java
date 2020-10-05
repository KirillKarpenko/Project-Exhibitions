package controller.command.implementation;

import controller.command.Command;
import model.service.AdminExpositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AdminDeleteExpositionButtonCommand implements Command {

    private static final AdminExpositionService adminExpositionService = new AdminExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(Optional.ofNullable(request.getParameter("exposition_id")).orElse("0"));

        if (id != 0)
            adminExpositionService.deleteById(id);

        return "redirect: /exhibitions/admin/expositions?expositionPage=1";
    }

}
