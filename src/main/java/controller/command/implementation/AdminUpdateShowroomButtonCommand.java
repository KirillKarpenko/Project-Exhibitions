package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.entity.Showroom;
import model.service.AdminExpositionService;
import model.service.AdminShowroomService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class AdminUpdateShowroomButtonCommand implements Command {

    private static final AdminShowroomService adminShowroomService = new AdminShowroomService();
    private static final AdminExpositionService adminExpositionService = new AdminExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String showroomToString = request.getParameter("showroom");

        if (showroomToString == null || showroomToString.isEmpty())
            return "redirect: /exhibitions/admin/showrooms?showroomPage=1";

        Showroom temp = new Showroom();
        StringTokenizer stringTokenizer = new StringTokenizer(showroomToString, ",");
        temp.setId(Integer.parseInt(stringTokenizer.nextToken()));
        temp.setName(stringTokenizer.nextToken());
        Exposition exposition = new Exposition();
        exposition.setId(Integer.parseInt(stringTokenizer.nextToken()));
        exposition.setName(stringTokenizer.nextToken());
        String category = stringTokenizer.nextToken();

        if (!category.equals("null"))
            exposition.setCategory(Exposition.Category.valueOf(category));

        String startDate = stringTokenizer.nextToken();

        if (!startDate.equals("null"))
            exposition.setStartDate(Timestamp.valueOf(startDate));

        String endDate = stringTokenizer.nextToken();

        if (!endDate.equals("null"))
            exposition.setEndDate(Timestamp.valueOf(endDate));

        String price = stringTokenizer.nextToken();

        if (!price.equals("null"))
            exposition.setPrice(Double.parseDouble(price));

        temp.setExposition(exposition);

        String name = request.getParameter("showroom_name");

        if (name == null || name.isEmpty())
            name = temp.getName();

        else if (adminShowroomService.isExists(name)) {
            List<String> expositionsNames = adminExpositionService.findExpositionsNames();
            request.setAttribute("expositionsNames", expositionsNames);
            request.setAttribute("showroom", temp);
            request.getSession().setAttribute("showroomExists", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.showroomExists"));
            return "/jsp/admin/update_showroom.jsp";
        }

        Showroom showroom = new Showroom();
        showroom.setId(temp.getId());
        showroom.setName(name);
        String bookedBy = request.getParameter("bookedBy");

        if (bookedBy == null || bookedBy.isEmpty())
            adminShowroomService.updateAndSetExpositionNull(showroom);

        else {
            showroom.setExposition(adminExpositionService.findByName(bookedBy));
            adminShowroomService.update(showroom);
        }

        return "redirect: /exhibitions/admin/showrooms?showroomPage=1";
    }

}
