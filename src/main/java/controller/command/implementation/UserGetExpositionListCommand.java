package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.service.UserExpositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserGetExpositionListCommand implements Command {

    private static final UserExpositionService userExpositionService = new UserExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int total;
        int expositionsPerPage = 5;
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int start = (page-1)*expositionsPerPage;
        List<Exposition.Category> categories = List.of(Exposition.Category.values());
        request.setAttribute("categories", categories);
        request.setAttribute("page", page);

        String category = request.getParameter("category");
        String startDate = request.getParameter("start_date");
        String endDate = request.getParameter("end_date");
        String sort = request.getParameter("sort");
        List<Exposition> expositions;

        if (category != null && !category.isEmpty() && startDate != null &&
                !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            LocalDate sd = LocalDate.parse(startDate);
            Timestamp startTimestamp = new Timestamp(sd.toEpochDay()*86400000);
            LocalDate ed = LocalDate.parse(endDate);
            Timestamp endTimestamp = new Timestamp(ed.toEpochDay()*86400000);
            Exposition.Category cat = Exposition.Category.valueOf(category);
            total = userExpositionService.categoryAndDateAmount(startTimestamp, endTimestamp, cat);

            if (sort == null || sort.isEmpty())
                expositions = userExpositionService.filterByDateAndCategoryByPage(startTimestamp, endTimestamp, cat, start, expositionsPerPage);

            else if (sort.equals("sort_by_price_asc"))
                expositions = userExpositionService.sortByPriceAscAndFilterByCategoryAndDateByPage(startTimestamp, endTimestamp, cat, start, expositionsPerPage);

            else
                expositions = userExpositionService.sortByPriceDescAndFilterByCategoryAndDateByPage(startTimestamp, endTimestamp, cat, start, expositionsPerPage);
        }

        else if (category != null && !category.isEmpty()) {
            Exposition.Category cat = Exposition.Category.valueOf(category);
            total = userExpositionService.categoryAmount(cat);

            if (sort == null || sort.isEmpty())
                expositions = userExpositionService.filterByCategoryByPage(cat, start, expositionsPerPage);

            else if (sort.equals("sort_by_price_asc"))
                expositions = userExpositionService.sortByPriceAscAndFilterByCategoryByPage(cat, start, expositionsPerPage);

            else
                expositions = userExpositionService.sortByPriceDescAndFilterByCategoryByPage(cat, start, expositionsPerPage);
        }

        else if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            LocalDate sd = LocalDate.parse(startDate);
            Timestamp startTimestamp = new Timestamp(sd.toEpochDay()*86400000);
            LocalDate ed = LocalDate.parse(endDate);
            Timestamp endTimestamp = new Timestamp(ed.toEpochDay()*86400000);
            total = userExpositionService.dateAmount(startTimestamp, endTimestamp);

            if (sort == null || sort.isEmpty())
                expositions = userExpositionService.filterByDateByPage(startTimestamp, endTimestamp, start, expositionsPerPage);

            else if (sort.equals("sort_by_price_asc"))
                expositions = userExpositionService.sortByPriceAscAndFilterByDateByPage(startTimestamp, endTimestamp, start, expositionsPerPage);

            else
                expositions = userExpositionService.sortByPriceDescAndFilterByDateByPage(startTimestamp, endTimestamp, start, expositionsPerPage);
        }

        else {
            total = userExpositionService.amount();

            if (sort == null || sort.isEmpty())
                expositions = userExpositionService.findByPage(start, expositionsPerPage);

            else if (sort.equals("sort_by_price_asc"))
                expositions = userExpositionService.sortByPriceAscByPage(start, expositionsPerPage);

            else
                expositions = userExpositionService.sortByPriceDescByPage(start, expositionsPerPage);
        }

        request.setAttribute("category", category);
        request.setAttribute("start_date", startDate);
        request.setAttribute("end_date", endDate);
        request.setAttribute("sort", sort);
        request.setAttribute("expositions", expositions);
        int totalExpositionPages = (int) Math.ceil((float) total/expositionsPerPage);
        request.setAttribute("totalPages", totalExpositionPages);
        return "/jsp/index.jsp";
    }

}
