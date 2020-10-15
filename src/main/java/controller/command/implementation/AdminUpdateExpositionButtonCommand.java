package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.service.AdminExpositionService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

public class AdminUpdateExpositionButtonCommand implements Command {

    private static final AdminExpositionService adminExpositionService = new AdminExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String expositionToString = request.getParameter("exposition");

        if (expositionToString == null || expositionToString.isEmpty())
            return "redirect: /exhibitions/admin/expositions?expositionPage=1";

        Exposition temp = new Exposition();
        StringTokenizer stringTokenizer = new StringTokenizer(expositionToString, ",");
        temp.setId(Integer.parseInt(stringTokenizer.nextToken()));
        temp.setName(stringTokenizer.nextToken());
        temp.setCategory(Exposition.Category.valueOf(stringTokenizer.nextToken()));
        temp.setStartDate(Timestamp.valueOf(stringTokenizer.nextToken()));
        temp.setEndDate(Timestamp.valueOf(stringTokenizer.nextToken()));
        temp.setPrice(Double.parseDouble(stringTokenizer.nextToken()));

        String name = request.getParameter("exposition_name");

        if (name == null || name.isEmpty())
            name = temp.getName();

        else if (adminExpositionService.isExists(name)) {
            List<String> cats = new ArrayList<>();
            List<Exposition.Category> categories = List.of(Exposition.Category.values());

            for (Exposition.Category category : categories)
                cats.add(ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getString("enum." + category.name().toLowerCase()));

            request.setAttribute("categories", cats);
            request.setAttribute("exposition", temp);
            request.getSession().setAttribute("expositionExists", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.expositionExists"));
            return "/jsp/admin/update_exposition.jsp";
        }

        final Exposition.Category[] category = new Exposition.Category[1];
        String cat = request.getParameter("category");
        Enumeration<String> keys = ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getKeys();
        Iterator<String> iterator = keys.asIterator();
        iterator.forEachRemaining(s -> {
            if (ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getString(s).equals(cat))
                category[0] = Exposition.Category.valueOf(ResourceBundle.getBundle("locale", Locale.ENGLISH).getString(s).toUpperCase());
        });

        if (category[0] == null)
            category[0] = temp.getCategory();

        String startTime = request.getParameter("start_date");
        Timestamp startDate;

        if (startTime == null || startTime.isEmpty())
            startDate = temp.getStartDate();

        else {
            LocalDate sd = LocalDate.parse(startTime);
            startDate = new Timestamp(sd.toEpochDay()*86400000);
        }

        String endTime = request.getParameter("end_date");
        Timestamp endDate;

        if (endTime == null || endTime.isEmpty())
            endDate = temp.getEndDate();

        else {
            LocalDate sd = LocalDate.parse(endTime);
            endDate = new Timestamp(sd.toEpochDay()*86400000);
        }

        double price;
        String priceString = request.getParameter("price");

        if (priceString == null || priceString.isEmpty())
            price = temp.getPrice();

        else
            price = Double.parseDouble(priceString);

        Exposition exposition = new Exposition();
        exposition.setId(temp.getId());
        exposition.setName(name);
        exposition.setCategory(category[0]);
        exposition.setStartDate(startDate);
        exposition.setEndDate(endDate);
        exposition.setPrice(price);
        String locale = request.getParameter("locale");

        if (locale == null)
            locale = request.getSession().getAttribute("lang").toString();

        ThreadLocalWrapper.setLocale(new Locale(locale));
        adminExpositionService.update(exposition);
        return "redirect: /exhibitions/admin/expositions?expositionPage=1";
    }

}
