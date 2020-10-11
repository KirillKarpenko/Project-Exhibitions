package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.service.AdminExpositionService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
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

        Exposition.Category category = Exposition.Category.valueOf(request.getParameter("category"));
        LocalDate sd = LocalDate.parse(startLocalDate);
        Timestamp startDate = new Timestamp(sd.toEpochDay()*86400000);
        LocalDate ed = LocalDate.parse(endLocalDate);
        Timestamp endDate = new Timestamp(ed.toEpochDay()*86400000);
        double price = Double.parseDouble(stingPrice);
        Exposition exposition = new Exposition();
        exposition.setName(name);
        exposition.setCategory(category);
        exposition.setStartDate(startDate);
        exposition.setEndDate(endDate);
        exposition.setPrice(price);
        adminExpositionService.create(exposition);
        return "redirect: /exhibitions/admin/expositions?expositionPage=1";
    }

}
