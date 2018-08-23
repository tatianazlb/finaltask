package epam.zlobich.task6.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "ValidationFilter", urlPatterns = { "/*" })
public class ValidationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)req;


        for (String[] s: request.getParameterMap().values()
             ) {
            for(int i =0; i<s.length; i++)
            {
                s[i]=s[i].replaceAll("[><]", "");
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
