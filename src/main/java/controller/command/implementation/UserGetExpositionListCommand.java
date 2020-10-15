package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.service.UserExpositionService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

public class UserGetExpositionListCommand implements Command {

    private static final UserExpositionService userExpositionService = new UserExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int total;
        int expositionsPerPage = 5;
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int start = (page-1)*expositionsPerPage;
        List<String> cats = new ArrayList<>();
        List<Exposition.Category> categories = List.of(Exposition.Category.values());

        for (Exposition.Category category : categories)
            cats.add(ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getString("enum." + category.name().toLowerCase()));

        request.setAttribute("all_categories", cats);
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
            final Exposition.Category[] cat = new Exposition.Category[1];
            Enumeration<String> keys = ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getKeys();
            Iterator<String> iterator = keys.asIterator();
            iterator.forEachRemaining(s -> {
                if (ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getString(s).equals(category))
                    cat[0] = Exposition.Category.valueOf(ResourceBundle.getBundle("locale", Locale.ENGLISH).getString(s).toUpperCase());
            });

            total = userExpositionService.categoryAndDateAmount(startTimestamp, endTimestamp, cat[0]);

            if (sort == null || sort.isEmpty())
                expositions = userExpositionService.filterByDateAndCategoryByPage(startTimestamp, endTimestamp, cat[0], start, expositionsPerPage);

            else if (sort.equals("sort_by_price_asc"))
                expositions = userExpositionService.sortByPriceAscAndFilterByCategoryAndDateByPage(startTimestamp, endTimestamp, cat[0], start, expositionsPerPage);

            else
                expositions = userExpositionService.sortByPriceDescAndFilterByCategoryAndDateByPage(startTimestamp, endTimestamp, cat[0], start, expositionsPerPage);
        }

        else if (category != null && !category.isEmpty()) {
            final Exposition.Category[] cat = new Exposition.Category[1];
            Enumeration<String> keys = ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getKeys();
            Iterator<String> iterator = keys.asIterator();
            iterator.forEachRemaining(s -> {
                if (ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getString(s).equals(category))
                    cat[0] = Exposition.Category.valueOf(ResourceBundle.getBundle("locale", Locale.ENGLISH).getString(s).toUpperCase());
            });
            total = userExpositionService.categoryAmount(cat[0]);

            if (sort == null || sort.isEmpty())
                expositions = userExpositionService.filterByCategoryByPage(cat[0], start, expositionsPerPage);

            else if (sort.equals("sort_by_price_asc"))
                expositions = userExpositionService.sortByPriceAscAndFilterByCategoryByPage(cat[0], start, expositionsPerPage);

            else
                expositions = userExpositionService.sortByPriceDescAndFilterByCategoryByPage(cat[0], start, expositionsPerPage);
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

        cats = new ArrayList<>();

        for (Exposition exposition : expositions)
            cats.add(ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getString("enum." + exposition.getCategory().name().toLowerCase()));

        request.setAttribute("categories", cats);
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
