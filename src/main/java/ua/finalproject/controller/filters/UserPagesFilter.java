package ua.finalproject.controller.filters;

import ua.finalproject.model.entities.impl.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "UserPagesFilter")

public class UserPagesFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Optional<User.Role> role = Optional.ofNullable((User.Role) request.getSession().getAttribute("role"));
        if (role.isPresent() && role.equals(Optional.of(User.Role.USER))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (role.isPresent() && role.equals(Optional.of(User.Role.ADMIN))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
