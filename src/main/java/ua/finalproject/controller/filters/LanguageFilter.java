package ua.finalproject.controller.filters;

import ua.finalproject.constants.filter.FilterNames;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.util.BundleManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * Filter for changing locale
 */
@WebFilter(filterName = FilterNames.LANGUAGE_FILTER)
public class LanguageFilter implements Filter {

    @Override
    public void destroy() {
    }

    /**
     * Getting language attribute from user's session and
     * change bundle's locale
     *
     * @param servletRequest  request
     * @param servletResponse response
     * @param filterChain     filter chain
     * @throws IOException      io exception
     * @throws ServletException servlet exception
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Locale locale;
        try {
            locale = (Locale) request.getSession().getAttribute(RequestAttributes.LANGUAGE);
            BundleManager.INSTANCE.changeLocale(locale);
            filterChain.doFilter(request, servletResponse);

        } catch (ClassCastException e) {
            locale = new Locale((String) request.getSession().getAttribute(RequestAttributes
                    .LANGUAGE));
            BundleManager.INSTANCE.changeLocale(locale);
            filterChain.doFilter(request, servletResponse);
        }

    }

    @Override
    public void init(FilterConfig config) {

    }

}
