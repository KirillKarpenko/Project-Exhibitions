package controller.command.implementation;

import controller.command.Command;
import model.entity.Account;
import model.service.UserAccountService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class UserUpdateAccountButtonCommand implements Command {

    private static final UserAccountService userAccountService = new UserAccountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Account account = (Account) request.getSession().getAttribute("account");

        if (account == null)
            return "redirect: /exhibitions/index?expositionPage=1";

        String login = request.getParameter("login");

        if (login == null || login.isEmpty())
            login = account.getLogin();

        else if (userAccountService.isExists(login)) {
            request.getSession().setAttribute("loginExists", ResourceBundle.getBundle("locale",
                    ThreadLocalWrapper.getLocale()).getString("error.loginExists"));
            return "redirect: /exhibitions/user/account_info/edit_account";
        }

        String firstName = request.getParameter("first_name");

        if (firstName == null || firstName.isEmpty())
            firstName = account.getFirstName();

        String lastName = request.getParameter("last_name");

        if (lastName == null || lastName.isEmpty())
            lastName = account.getLastName();

        String password = request.getParameter("password");

        if (password == null || password.isEmpty())
            password = account.getPassword();

        Account temp = new Account();
        temp.setId(account.getId());
        temp.setLogin(login);
        temp.setPassword(password);
        temp.setFirstName(firstName);
        temp.setLastName(lastName);
        temp.setRole(Account.Role.USER);

        if (validate(request, temp)) {
            userAccountService.update(temp);
            request.getSession().setAttribute("account", temp);
            return "redirect: /exhibitions/user/account_info";
        }

        else
            return "redirect: /exhibitions/user/account_info/edit_account";
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
