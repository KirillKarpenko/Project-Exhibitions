package controller.filter;

import util.ThreadLocalWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

@WebFilter(filterName = "localeFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Optional.ofNullable(request.getParameter("locale")).ifPresent(x -> {
            request.getSession().setAttribute("lang", x);
            ThreadLocalWrapper.setLocale(new Locale(x));
        });

        if (request.getParameter("locale") == null) {
            try {
                ThreadLocalWrapper.setLocale(new Locale((String) request.getSession().getAttribute("lang")));
            } catch (NullPointerException ex) {
                ThreadLocalWrapper.setLocale(Locale.getDefault());
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
