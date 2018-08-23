package epam.zlobich.task6.servlet;

import epam.zlobich.task6.command.AbstractCommand;
import epam.zlobich.task6.command.CommandEnum;
import epam.zlobich.task6.command.LoginCommand;
import epam.zlobich.task6.pool.ConnectionPool;
import epam.zlobich.task6.pool.PropertyClass;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "MainController", urlPatterns = {"/main", "/theme", "/lecture", "/register", "/conference", "/myroom"})
@MultipartConfig
public class MainController extends HttpServlet {

    private AbstractCommand commandCheck;

    @Override
    public void init() throws ServletException {

        Properties properties = new Properties();
        properties.setProperty("url", this.getServletContext().getInitParameter("databaseurl"));
        properties.setProperty("user", this.getServletContext().getInitParameter("databaseuser"));
        properties.setProperty("password", this.getServletContext().getInitParameter("databasepassword"));
        PropertyClass.setProperties(properties);
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        proceedR(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        proceedR(request, response);
    }

    protected void proceedR(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String command = request.getParameter("command");

        AbstractCommand exCommand = command==null? commandCheck : CommandEnum.valueOf(command.toUpperCase()).getCommand();

        Router router = exCommand.execute(request);

        while (router.getType()==TypeOfProceedRequest.FORWARD && router.getNextCommand()!=null)
            router=router.getNextCommand().execute(request);

        if(router.getType()==TypeOfProceedRequest.FORWARD)
        {
            request.getRequestDispatcher(router.getJspAdress()).forward(request, response);
        }
        else
        {
            commandCheck = router.getNextCommand();
            response.sendRedirect("/main");
        }
    }

}
