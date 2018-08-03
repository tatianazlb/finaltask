package epam.zlobich.task6.filter;

import epam.zlobich.task6.entity.entitybd.UserBD;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns = { "/*" })
public class SecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        UserBD current = (UserBD) session.getAttribute("user");
        if (current == null && request.getParameter("login")==null && request.getParameter("pass")==null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/password.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
