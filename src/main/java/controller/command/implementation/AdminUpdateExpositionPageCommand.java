package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import java.util.StringTokenizer;

public class AdminUpdateExpositionPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String expositionToString = request.getParameter("exposition");

        if (expositionToString == null || expositionToString.isEmpty())
            return "redirect: /exhibitions/admin/expositions?expositionPage=1";

        Exposition exposition = new Exposition();
        StringTokenizer stringTokenizer = new StringTokenizer(expositionToString, ",");
        exposition.setId(Integer.parseInt(stringTokenizer.nextToken()));
        exposition.setName(stringTokenizer.nextToken());
        exposition.setCategory(Exposition.Category.valueOf(stringTokenizer.nextToken()));
        exposition.setStartDate(Timestamp.valueOf(stringTokenizer.nextToken()));
        exposition.setEndDate(Timestamp.valueOf(stringTokenizer.nextToken()));
        exposition.setPrice(Double.parseDouble(stringTokenizer.nextToken()));
        request.setAttribute("exposition", exposition);
        List<Exposition.Category> categories = List.of(Exposition.Category.values());
        request.setAttribute("categories", categories);
        return "/jsp/admin/update_exposition.jsp";
    }

}
