package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminCreateExpositionPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<String> cats = new ArrayList<>();
        List<Exposition.Category> categories = List.of(Exposition.Category.values());

        for (Exposition.Category category : categories)
            cats.add(ResourceBundle.getBundle("locale", ThreadLocalWrapper.getLocale()).getString("enum." + category.name().toLowerCase()));

        request.setAttribute("categories", cats);
        return "/jsp/admin/create_exposition.jsp";
    }

}
