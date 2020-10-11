package util;

import controller.Servlet;
import model.entity.Account;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SecurityManager {

    private static final ServletContext context = Servlet.getContext();

    private Integer getAccountId(HttpSession session) {
        Optional<Account> account = Optional.ofNullable(((Account) session.getAttribute("account")));
        return account.map(Account::getId).orElse(0);
    }

    public void logOut(HttpSession session) {
        Map<Integer, HttpSession> loggedAccounts = getLoggedAccounts();
        loggedAccounts.remove(getAccountId(session));
        setLoggedAccounts(loggedAccounts);
        session.removeAttribute("account");
    }

    public void setLoggedAccounts(Map<Integer, HttpSession> loggedAccounts) {
        context.setAttribute("loggedAccounts", loggedAccounts);
    }

    public Map<Integer, HttpSession> getLoggedAccounts() {
        return Optional.ofNullable((HashMap<Integer, HttpSession>) context.getAttribute("loggedAccounts")).orElse(new HashMap<>());
    }

}
