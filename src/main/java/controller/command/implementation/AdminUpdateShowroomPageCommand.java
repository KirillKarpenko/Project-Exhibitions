package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.entity.Showroom;
import model.service.AdminExpositionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import java.util.StringTokenizer;

public class AdminUpdateShowroomPageCommand implements Command {

    AdminExpositionService adminExpositionService = new AdminExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String showroomToString = request.getParameter("showroom");

        if (showroomToString == null || showroomToString.isEmpty())
            return "redirect: /exhibitions/admin/showrooms?showroomPage=1";

        Showroom showroom = new Showroom();
        Exposition exposition = new Exposition();
        StringTokenizer stringTokenizer = new StringTokenizer(showroomToString, ",");
        showroom.setId(Integer.parseInt(stringTokenizer.nextToken()));
        showroom.setName(stringTokenizer.nextToken());
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

        showroom.setExposition(exposition);
        request.setAttribute("showroom", showroom);
        List<String> expositionsNames = adminExpositionService.findExpositionsNames();
        request.setAttribute("expositionsNames", expositionsNames);
        return "/jsp/admin/update_showroom.jsp";
    }

}
