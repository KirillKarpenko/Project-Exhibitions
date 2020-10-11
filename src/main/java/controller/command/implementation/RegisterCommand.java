package controller.command.implementation;

import controller.command.Command;
import model.entity.Account;
import model.service.RegisterService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegisterCommand implements Command {

    private static final RegisterService registerService = new RegisterService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Account account = (Account) request.getSession().getAttribute("account");

        if (account != null)
            return "redirect: /exhibitions/index?expositionPage=1";

        account = setUpAccount(request);

        if (!validate(request, account))
            return "redirect: /exhibitions/register";

        else if (registerService.isExists(account.getLogin())) {
            request.getSession().setAttribute("loginExists", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.loginExists"));
            return "redirect: /exhibitions/register";
        }

        else {
            registerService.registerAccount(account);
            request.getSession().setAttribute("account", account);
            return "redirect: /exhibitions/index?expositionPage=1";
        }
    }

    private Account setUpAccount(HttpServletRequest request) {
        Account account = new Account();
        account.setRole(Account.Role.USER);
        account.setLogin(request.getParameter("login"));
        account.setPassword(request.getParameter("password"));
        account.setFirstName(request.getParameter("first_name"));
        account.setLastName(request.getParameter("last_name"));
        return account;
    }

    private boolean validate(HttpServletRequest request, Account account) {
        boolean flag = true;
        Pattern loginPattern = Pattern.compile("\\w{5,32}");
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$");
        Pattern firstNamePattern = Pattern.compile("[A-ZА-Я][a-zа-я]{1,31}");
        Pattern lastNamePattern = Pattern.compile("[A-ZА-Я][a-zа-я]{1,31}");

        if (!loginPattern.matcher(account.getLogin()).matches()) {
            request.getSession().setAttribute("wrongUsername", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.wrongUsername"));
            flag = false;
        }

        if (!passwordPattern.matcher(account.getPassword()).matches()) {
            request.getSession().setAttribute("wrongPassword", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.wrongPassword"));
            flag = false;
        }

        if (!firstNamePattern.matcher(account.getFirstName()).matches()) {
            request.getSession().setAttribute("wrongFirstName", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.wrongFirstName"));
            flag = false;
        }

        if (!lastNamePattern.matcher(account.getLastName()).matches()) {
            request.getSession().setAttribute("wrongLastName", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.wrongLastName"));
            flag = false;
        }

        return flag;
    }

}
