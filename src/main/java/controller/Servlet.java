package controller;

import controller.command.Command;
import controller.command.implementation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/exhibitions/*", loadOnStartup = 1)
public class Servlet extends HttpServlet {

    private static final Map<String, Command> commands = new HashMap<>();
    private static ServletContext context;

    @Override
    public void init(ServletConfig config) {
        context = config.getServletContext();
        commands.put("index", new UserGetExpositionListCommand());
        commands.put("login", new LoginPage());
        commands.put("login/login_button", new LoginCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("register", new RegisterPage());
        commands.put("register/register_button", new RegisterCommand());
        commands.put("admin", new AdminCommand());
        commands.put("admin/create_exposition/create_exposition_button", new AdminCreateExpositionButtonCommand());
        commands.put("admin/create_exposition", new AdminCreateExpositionPageCommand());
        commands.put("admin/create_showroom/create_showroom_button", new AdminCreateShowroomButtonCommand());
        commands.put("admin/create_showroom", new AdminCreateShowroomPageCommand());
        commands.put("admin/expositions/delete_exposition_button", new AdminDeleteExpositionButtonCommand());
        commands.put("admin/showrooms/delete_showroom_button", new AdminDeleteShowroomButtonCommand());
        commands.put("admin/expositions", new AdminGetExpositionListCommand());
        commands.put("admin/showrooms", new AdminGetShowroomListCommand());
        commands.put("admin/unpaid_booking", new AdminGetUnpaidBookingCommand());
        commands.put("admin/expositions/update_exposition/update_exposition_button", new AdminUpdateExpositionButtonCommand());
        commands.put("admin/expositions/update_exposition", new AdminUpdateExpositionPageCommand());
        commands.put("admin/unpaid_booking/update_booking_button", new AdminUpdatePaidInformationButtonCommand());
        commands.put("admin/showrooms/update_showroom/update_showroom_button", new AdminUpdateShowroomButtonCommand());
        commands.put("admin/showrooms/update_showroom", new AdminUpdateShowroomPageCommand());
        commands.put("user", new UserCommand());
        commands.put("user/add_to_cart", new UserAddToCartButtonCommand());
        commands.put("user/account_info/delete_account", new UserDeleteAccountButtonCommand());
        commands.put("user/bookings", new UserGetAccountBookingListCommand());
        commands.put("user/account_info", new UserGetAccountInfoCommand());
        commands.put("user/cart", new UserGetCartListCommand());
        commands.put("filter_by_category", new UserGetExpositionListFilterByCategoryCommand());
        commands.put("filter_by_date", new UserGetExpositionListFilterByDateCommand());
        commands.put("sort_by_price_asc", new UserGetExpositionListSortByPriceAscCommand());
        commands.put("sort_by_price_desc", new UserGetExpositionListSortByPriceDescCommand());
        commands.put("user/cart/remove_from_cart", new UserRemoveFromCartButtonCommand());
        commands.put("user/cart/send_order", new UserSendOrderButtonCommand());
        commands.put("user/account_info/edit_account/edit_account_button", new UserUpdateAccountButtonCommand());
        commands.put("user/account_info/edit_account", new UserUpdateAccountPageCommand());
    }

    public static ServletContext getContext() {
        return context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getRequestURI().replaceAll(".*/exhibitions/", "").replaceAll(".*/exhibitions", "");
        Command command = commands.getOrDefault(path, (r, j) -> "/exhibitions/index");
        String page = command.execute(req, resp);

        if (page != null) {
            if (page.contains("redirect: ")) {
                try {
                    resp.sendRedirect(req.getContextPath() + page.replace("redirect: ", ""));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else {
                try {
                    req.getRequestDispatcher(page).forward(req, resp);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
