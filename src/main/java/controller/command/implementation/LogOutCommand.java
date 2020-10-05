package controller.command.implementation;

import controller.command.Command;
import util.SecurityManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        new SecurityManager().logOut(request.getSession());
        return "redirect: /exhibitions/index?expositionPage=1";
    }

}
