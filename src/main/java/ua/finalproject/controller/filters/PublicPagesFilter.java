package ua.finalproject.controller.filters;

import ua.finalproject.model.entities.impl.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = {"/taxi/index", "/taxi/login", "/"}, filterName = "PublicPagesFilter")
public class PublicPagesFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Optional<User.Role> role = Optional.ofNullable((User.Role) request.getSession().getAttribute("role"));
        if (role.isPresent() && role.equals(Optional.of(User.Role.ADMIN))) {
            request.getRequestDispatcher("/WEB-INF/admin/admin_foundation.jsp").forward(servletRequest, servletResponse);
        }
        if (role.isPresent() && role.equals(Optional.of(User.Role.USER))) {
            request.getRequestDispatcher("/WEB-INF/user/user_foundation.jsp").forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
