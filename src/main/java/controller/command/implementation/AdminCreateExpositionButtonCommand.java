package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.service.AdminExpositionService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminCreateExpositionButtonCommand implements Command {

    private static final AdminExpositionService adminExpositionService = new AdminExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("exposition_name");
        String startLocalDate = request.getParameter("start_date");
        String endLocalDate = request.getParameter("end_date");
        String stingPrice = request.getParameter("price");

        if (name == null || name.isEmpty() || startLocalDate == null || startLocalDate.isEmpty() || endLocalDate == null || endLocalDate.isEmpty() || stingPrice == null || stingPrice.isEmpty()) {
            request.getSession().setAttribute("enterAllFields", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.enterAllFields"));
            return "redirect: /exhibitions/admin/create_exposition";
        }

        if (adminExpositionService.isExists(name)) {
            request.getSession().setAttribute("expositionExists", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.expositionExists"));
            return "redirect: /exhibitions/admin/create_exposition";
        }

        final Exposition.Category[] category = new Exposition.Category[1];
        String cat = request.getParameter("category");
        Enumeration<String> keys = ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getKeys();
        Iterator<String> iterator = keys.asIterator();
        iterator.forEachRemaining(s -> {
            if (ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getString(s).equals(cat))
                category[0] = Exposition.Category.valueOf(ResourceBundle.getBundle("locale", Locale.ENGLISH).getString(s).toUpperCase());
        });

        LocalDate sd = LocalDate.parse(startLocalDate);
        Timestamp startDate = new Timestamp(sd.toEpochDay()*86400000);
        LocalDate ed = LocalDate.parse(endLocalDate);
        Timestamp endDate = new Timestamp(ed.toEpochDay()*86400000);
        double price = Double.parseDouble(stingPrice);
        Exposition exposition = new Exposition();
        exposition.setName(name);
        exposition.setCategory(category[0]);
        exposition.setStartDate(startDate);
        exposition.setEndDate(endDate);
        exposition.setPrice(price);
        String locale = request.getParameter("locale");

        if (locale == null)
            locale = request.getSession().getAttribute("lang").toString();

        ThreadLocalWrapper.setLocale(new Locale(locale));
        adminExpositionService.create(exposition);
        return "redirect: /exhibitions/admin/expositions?expositionPage=1";
    }

}
