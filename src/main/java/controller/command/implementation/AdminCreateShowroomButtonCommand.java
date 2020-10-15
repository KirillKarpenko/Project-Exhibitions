package controller.command.implementation;

import controller.command.Command;
import model.entity.Showroom;
import model.service.AdminExpositionService;
import model.service.AdminShowroomService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminCreateShowroomButtonCommand implements Command {

    private static final AdminShowroomService adminShowroomService = new AdminShowroomService();
    private static final AdminExpositionService adminExpositionService = new AdminExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("showroom_name");

        if (name == null || name.isEmpty()) {
            request.getSession().setAttribute("enterAllFields", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.enterAllFields"));
            return "redirect: /exhibitions/admin/create_showroom";
        }

        if (adminShowroomService.isExists(name)) {
            request.getSession().setAttribute("showroomExists", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.showroomExists"));
            return "redirect: /exhibitions/admin/create_showroom";
        }

        Showroom showroom = new Showroom();
        showroom.setName(name);
        String expositionName = request.getParameter("exposition_name");

        if (expositionName == null || expositionName.isEmpty() || expositionName.equals("null"))
            adminShowroomService.createWithoutExposition(showroom);

        else {
            showroom.setExposition(adminExpositionService.findByName(expositionName));
            adminShowroomService.create(showroom);
        }

        String locale = request.getParameter("locale");

        if (locale == null)
            locale = request.getSession().getAttribute("lang").toString();

        ThreadLocalWrapper.setLocale(new Locale(locale));
        return "redirect: /exhibitions/admin/showrooms?showroomPage=1";
    }

}
