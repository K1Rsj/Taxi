package ua.finalproject.controller.filters;

import ua.finalproject.constants.filter.FilterNames;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.model.entities.impl.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * Filter for commands that only administrator can execute
 */
@WebFilter(filterName = FilterNames.ADMIN_PAGES_FILTER)
public class AdminPagesFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * If someone than admin execute admin's commands he will
     * be redirected to his index page.
     * @param servletRequest request
     * @param servletResponse response
     * @param filterChain filter chain
     * @throws IOException io exception
     * @throws ServletException servlet exception
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Optional<User.Role> role = Optional.ofNullable((User.Role) request.getSession().getAttribute(RequestAttributes.ROLE));
        if (role.isPresent() && role.equals(Optional.of(User.Role.USER))) {
            request.getRequestDispatcher(JSPPages.USER_FOUNDATION_PAGE).forward(servletRequest, servletResponse);
        } else if (role.isPresent() && role.equals(Optional.of(User.Role.ADMIN))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.getRequestDispatcher(JSPPages.INDEX_PAGE).forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
