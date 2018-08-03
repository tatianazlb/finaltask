package epam.zlobich.task6.servlet;

import epam.zlobich.task6.entity.entitybd.Conference;
import epam.zlobich.task6.entity.entitybd.UserBD;
import epam.zlobich.task6.service.ConferenceService;
import epam.zlobich.task6.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

@WebServlet(name = "PasswordServlet", urlPatterns = "/password")
public class PasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPassword(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPassword(request, response);
    }

    protected void doPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        UserBD currUser = new UserService().checkUser(request.getParameter("login"), request.getParameter("pass"));

        ResourceBundle RB =(ResourceBundle)request.getSession().getAttribute("bundle");


        if (currUser==null)
        {
            request.setAttribute("sorry", RB.getString("sorry"));
            request.getRequestDispatcher("password.jsp").forward(request, response);
        }
        else if(!currUser.isRole()){


            ArrayList<Conference> c = new ConferenceService().findUserConferences(currUser);

            String s = "<ul>";

            for (Conference cc : c
                    ) {
                s += cc.toListForAdmin();
            }
            s += "</ul>";

            //todo: conn to database and check password by name

            request.getSession().setAttribute("user", currUser);

            request.setAttribute("tableMy", RB.getString("myconf"));

            request.setAttribute("myConf", s);


            request.setAttribute("name", "Hello, " + currUser.getLogin() + "!");
            request.getRequestDispatcher("conference.jsp").forward(request, response);
        }
        else if (currUser.isRole()){

            ArrayList<Conference> c = (ArrayList)new ConferenceService().findAll();
            String s = "<ul>";
            for (Conference cc : c
                    ) {
                s += cc.toListForAdmin();
            }
            s += "</ul>";

            request.setAttribute("tableAll", RB.getString("exconf"));

            request.getSession().setAttribute("user", currUser);

            request.setAttribute("allConf", s);
            request.setAttribute("button", "\n" +
                    "<button>"+RB.getString("addconf")+"</button>");


            request.setAttribute("name", "Hello, " + currUser.getLogin() + "!");
            request.getRequestDispatcher("conference.jsp").forward(request, response);
        }
    }
}
