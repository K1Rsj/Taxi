package ua.finalproject.controller.filters;

import ua.finalproject.constants.GlobalConstants;
import ua.finalproject.constants.filter.FilterNames;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Filter for encoding request and response content
 */
@WebFilter(filterName = FilterNames.ENCODING_FILTER)
public class EncodingFilter implements Filter {

    @Override
    public void destroy() {
    }

    /**
     * Encoding request and response content
     *
     * @param servletRequest  request
     * @param servletResponse response
     * @param filterChain     filter chain
     * @throws IOException      io exception
     * @throws ServletException servlet exception
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse
            servletResponse,
                         FilterChain filterChain) throws IOException,
            ServletException {
        servletResponse.setContentType(GlobalConstants.TEXT_HTML);
        servletResponse.setCharacterEncoding(GlobalConstants.UTF_8);
        servletRequest.setCharacterEncoding(GlobalConstants.UTF_8);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig config) {

    }

}
