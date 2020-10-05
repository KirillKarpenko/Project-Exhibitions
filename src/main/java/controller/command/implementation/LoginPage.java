package controller.command.implementation;

import controller.command.Command;
import model.entity.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Account account = (Account) request.getSession().getAttribute("account");

        if (account != null)
            return "redirect: /exhibitions/index?expositionPage=1";

        return "/jsp/login.jsp";
    }

}
