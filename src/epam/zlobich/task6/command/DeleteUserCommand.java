package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.UserBd;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand extends AbstractCommand
{
    @Override
    public Router execute(HttpServletRequest request) {

        try {

            new UserService().deleteUser((UserBd) request.getSession().getAttribute("user"));

            request.getSession().invalidate();

            return new Router(TypeOfProceedRequest.REDIRECT, JspPath.PASSWORD_JSP, null);
        } catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.ERROR_JSP, null);
        }
    }
}
