package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.UserBd;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class LoginCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request){

        UserBd currUser = null;
        ResourceBundle rb = (ResourceBundle)request.getSession().getAttribute("bundle");

        try {
            currUser = new UserService().checkUser(request.getParameter("login"), request.getParameter("password"));

        } catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.ERROR_JSP, null);
        }

        if (currUser==null)
        {
            request.setAttribute("sorry", rb.getString("sorry"));

            return new Router(TypeOfProceedRequest.FORWARD,JspPath.PASSWORD_JSP, null);
        }
        else {
            request.getSession().setAttribute("user", currUser);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.CONFERENCE_JSP, new ConferenceCommand());
        }
    }
}
