package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.UserBd;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class RegisterCommand extends AbstractCommand{
    @Override
    public Router execute(HttpServletRequest request) {

        try {
            ResourceBundle rb =(ResourceBundle)request.getSession().getAttribute("bundle");

            UserBd user = new UserBd();
            user.setRole(false);
            user.setHomeland(request.getParameter("homeland"));
            user.setLogin(request.getParameter("login"));

            if (new UserService().addUser(user, request.getParameter("pass"))) {
                request.setAttribute("registercheck", rb.getString("usercreated"));
            } else request.setAttribute("registercheck", rb.getString("samelogin"));

            return new Router(TypeOfProceedRequest.FORWARD, JspPath.PASSWORD_JSP, null);
        }
        catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.ERROR_JSP, null);
        }
    }
}
