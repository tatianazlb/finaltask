package epam.zlobich.task6.filter;

import epam.zlobich.task6.command.JspPath;
import epam.zlobich.task6.entity.entitybd.UserBd;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns = { "/*" })
public class SecurityFilter implements Filter {

    private final static Logger LOGGER = LogManager.getLogger(SecurityFilter.class);
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        UserBd current = (UserBd) session.getAttribute("user");

        if (current == null && request.getParameter("login")==null && request.getParameter("pass")==null && !request.getRequestURL().toString().equals("http://localhost:9999/locale")) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/"+ JspPath.PASSWORD_JSP);
            dispatcher.forward(req, resp);
            return;
        }
        try {
            chain.doFilter(req, resp);
        }
        catch (ServletException e)
        {
            req.setAttribute("exception", e);
            request.getServletContext().getRequestDispatcher("/"+ JspPath.ERROR_JSP).forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
