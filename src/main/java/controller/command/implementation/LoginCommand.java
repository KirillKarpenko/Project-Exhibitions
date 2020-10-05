package controller.command.implementation;

import controller.command.Command;
import model.entity.Account;
import model.service.LoginService;
import util.LocaleManager;
import util.SecurityManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private static final SecurityManager securityManager = new SecurityManager();
    private static final LoginService loginService = new LoginService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Account account = (Account) request.getSession().getAttribute("account");

        if (account != null)
            return "redirect: /exhibitions/index?expositionPage=1";

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        account = loginService.validateAccount(login, password);

        try {
            Map<Integer, HttpSession> loggedAccounts = securityManager.getLoggedAccounts();
            int accountID = account.getId();

            if (loggedAccounts.containsKey(accountID))
                loggedAccounts.get(accountID).invalidate();

            loggedAccounts.put(accountID, request.getSession());
            securityManager.setLoggedAccounts(loggedAccounts);
            request.getSession().setAttribute("account", account);

            if (account.getRole() == Account.Role.ADMIN)
                return "redirect: /exhibitions/admin";

            else
                return "redirect: /exhibitions/index?expositionPage=1";
        } catch (RuntimeException e) {
            request.setAttribute("wrongUsernameOrPassword", LocaleManager.getString("error.wrongUsernameOrPassword"));
            return "/jsp/login.jsp";
        }
    }


}
