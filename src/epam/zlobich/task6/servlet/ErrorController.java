package epam.zlobich.task6.servlet;

import epam.zlobich.task6.command.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ErrorController", urlPatterns = {"/error"})
public class ErrorController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proceed(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proceed(request, response);
    }


    protected void proceed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("exception", new ServletException("Database go wrong"));
        request.getRequestDispatcher(JspPath.ERROR_JSP).forward(request, response);
    }
}
