package controller.command.implementation;

import controller.command.Command;
import model.entity.Account;
import model.service.UserAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserDeleteAccountButtonCommand implements Command {

    private static final UserAccountService userAccountService = new UserAccountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null)
            return "redirect: /exhibitions/index?expositionPage=1";

        userAccountService.deleteById(account.getId());
        session.removeAttribute("account");
        session.invalidate();
        return "redirect: /exhibitions/index?expositionPage=1";
    }

}
