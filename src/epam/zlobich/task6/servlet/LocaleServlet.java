package epam.zlobich.task6.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "LocaleServlet", urlPatterns = "/locale")
public class LocaleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResourceBundle rb = ResourceBundle.getBundle("app", new Locale(request.getParameter("lbutton")));
        request.getSession().setAttribute("bundle", rb);
        request.getRequestDispatcher(PageName.INDEX_PAGE.getPageName()).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResourceBundle rb = ResourceBundle.getBundle("app", new Locale(request.getParameter("lbutton")));
        request.getSession().setAttribute("bundle", rb);
        request.getRequestDispatcher(PageName.INDEX_PAGE.getPageName()).forward(request, response);
    }
}
