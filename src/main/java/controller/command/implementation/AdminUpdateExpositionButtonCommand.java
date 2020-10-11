package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.service.AdminExpositionService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

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
            List<Exposition.Category> categories = List.of(Exposition.Category.values());
            request.setAttribute("categories", categories);
            request.setAttribute("exposition", temp);
            request.getSession().setAttribute("expositionExists", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.expositionExists"));
            return "/jsp/admin/update_exposition.jsp";
        }

        Exposition.Category category = Exposition.Category.valueOf(Optional.ofNullable(request.getParameter("category")).orElse(temp.getCategory().name()));

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
        exposition.setCategory(category);
        exposition.setStartDate(startDate);
        exposition.setEndDate(endDate);
        exposition.setPrice(price);
        adminExpositionService.update(exposition);
        return "redirect: /exhibitions/admin/expositions?expositionPage=1";
    }

}
