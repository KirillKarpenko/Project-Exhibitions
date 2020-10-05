package controller.command.implementation;

import controller.command.Command;
import model.entity.Showroom;
import model.service.AdminShowroomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class AdminGetShowroomListCommand implements Command {

    private static final AdminShowroomService adminShowroomService = new AdminShowroomService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int total = adminShowroomService.amount();
        int showroomsPerPage = 10;
        int totalShowroomPages = (int) Math.ceil((float) total/showroomsPerPage);
        request.setAttribute("totalShowroomPages", totalShowroomPages);
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("showroomPage")).orElse("1"));
        int start = (page-1)*showroomsPerPage;
        List<Showroom> showrooms = adminShowroomService.findByPage(start, showroomsPerPage);
        request.setAttribute("showrooms", showrooms);
        request.setAttribute("showroomPage", page);
        return "/jsp/admin/showrooms.jsp";
    }

}
