package ua.finalproject.controller.filters;

import ua.finalproject.model.entities.impl.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/taxi/index", "/taxi/login", "/", "/index.jsp", "/login.jsp"}, filterName = "PublicPagesFilter")
public class PublicPagesFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        User.Role role = (User.Role) request.getSession().getAttribute("role");
        if (role == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (role.equals(User.Role.ADMIN)) {
            request.getRequestDispatcher("/WEB-INF/admin/admin_foundation.jsp").forward(servletRequest, servletResponse);
        } else if (role.equals(User.Role.USER)) {
            request.getRequestDispatcher("/WEB-INF/user/user_foundation.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
