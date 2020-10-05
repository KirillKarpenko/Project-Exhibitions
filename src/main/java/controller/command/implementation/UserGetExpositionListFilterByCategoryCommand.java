package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.service.UserExpositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class UserGetExpositionListFilterByCategoryCommand implements Command {

    private static final UserExpositionService userExpositionService = new UserExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Exposition.Category category = Exposition.Category.valueOf(request.getParameter("category"));
        int total = userExpositionService.categoryAmount(category);
        int expositionsPerPage = 5;
        int totalCategoryExpositionPages = (int) Math.ceil((float) total/expositionsPerPage);
        request.setAttribute("totalPages", totalCategoryExpositionPages);
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int start = (page-1)*expositionsPerPage;
        List<Exposition> categoryExpositions = userExpositionService.filterByCategoryByPage(category, start, total);
        List<Exposition.Category> categories = List.of(Exposition.Category.values());
        request.setAttribute("categories", categories);
        request.setAttribute("expositions", categoryExpositions);
        request.setAttribute("page", page);
        return "/jsp/filter_by_category.jsp";
    }

}
