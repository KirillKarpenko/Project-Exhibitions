package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.service.AdminExpositionService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminGetExpositionListCommand implements Command {

    private static final AdminExpositionService adminExpositionService = new AdminExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int total = adminExpositionService.amount();
        int expositionsPerPage = 5;
        int totalExpositionPages = (int) Math.ceil((float) total/expositionsPerPage);
        request.setAttribute("totalExpositionPages", totalExpositionPages);
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("expositionPage")).orElse("1"));
        int start = (page-1)*expositionsPerPage;
        List<Exposition> expositions = adminExpositionService.findByPage(start, expositionsPerPage);
        List<String> cats = new ArrayList<>();

        for (Exposition exposition : expositions)
            cats.add(ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getString("enum." + exposition.getCategory().name().toLowerCase()));

        request.setAttribute("categories", cats);
        request.setAttribute("expositions", expositions);
        request.setAttribute("expositionPage", page);
        return "/jsp/admin/expositions.jsp";
    }

}
