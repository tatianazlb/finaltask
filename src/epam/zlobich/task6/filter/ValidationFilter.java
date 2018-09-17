package epam.zlobich.task6.filter;

import epam.zlobich.task6.requestwrapper.ValidationRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "ValidationFilter", urlPatterns = { "/*" })
public class ValidationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        ValidationRequest request = new ValidationRequest((HttpServletRequest)req);

        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
