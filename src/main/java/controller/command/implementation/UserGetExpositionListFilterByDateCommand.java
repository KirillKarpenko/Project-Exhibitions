package controller.command.implementation;

import controller.command.Command;
import model.entity.Exposition;
import model.service.UserExpositionService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserGetExpositionListFilterByDateCommand implements Command {

    private static final UserExpositionService userExpositionService = new UserExpositionService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Exposition.Category> categories = List.of(Exposition.Category.values());
        request.setAttribute("categories", categories);
        String startLocalDate = request.getParameter("start_date");
        String endLocalDate = request.getParameter("end_date");

        if (startLocalDate == null || startLocalDate.isEmpty() || endLocalDate == null || endLocalDate.isEmpty()) {
            request.getSession().setAttribute("enterDates", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.enterDates"));
            return "redirect: /exhibitions/index.jsp";
        }

        LocalDate sd = LocalDate.parse(startLocalDate);
        Timestamp startDate = new Timestamp(sd.toEpochDay()*86400000);
        LocalDate ed = LocalDate.parse(endLocalDate);
        Timestamp endDate = new Timestamp(ed.toEpochDay()*86400000);
        int total = userExpositionService.dateAmount(startDate, endDate);
        int expositionsPerPage = 5;
        int totalDateExpositionPages = (int) Math.ceil((float) total/expositionsPerPage);
        request.setAttribute("totalPages", totalDateExpositionPages);
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int start = (page-1)*expositionsPerPage;
        List<Exposition> dateExpositions = userExpositionService.filterByDateByPage(startDate, endDate, start, expositionsPerPage);
        request.setAttribute("expositions", dateExpositions);
        request.setAttribute("page", page);
        return "/jsp/filter_by_date.jsp";
    }

}
