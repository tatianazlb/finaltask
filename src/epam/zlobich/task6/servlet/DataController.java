package epam.zlobich.task6.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DataController", urlPatterns = {"/personalpage", "/themepage"})
public class DataController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proceed(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proceed(request, response);
    }


    protected void proceed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
