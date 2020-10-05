package controller.filter;

import model.entity.Account;
import model.exception.PageAccessException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/exhibitions/admin/*"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null || !account.getRole().toString().equalsIgnoreCase("admin"))
            throw new PageAccessException("You don't have permission to visit this page");

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
