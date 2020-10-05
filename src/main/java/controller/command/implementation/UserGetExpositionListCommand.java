package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.service.UserExpositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class UserGetExpositionListCommand implements Command {

    private static final UserExpositionService userExpositionService = new UserExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int total = userExpositionService.amount();
        int expositionsPerPage = 5;
        int totalExpositionPages = (int) Math.ceil((float) total/expositionsPerPage);
        request.setAttribute("totalPages", totalExpositionPages);
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int start = (page-1)*expositionsPerPage;
        List<Exposition> expositions = userExpositionService.findByPage(start, expositionsPerPage);
        List<Exposition.Category> categories = List.of(Exposition.Category.values());
        request.setAttribute("categories", categories);
        request.setAttribute("expositions", expositions);
        request.setAttribute("page", page);
        return "/jsp/index.jsp";
    }

}
