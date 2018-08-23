package epam.zlobich.task6.command;

import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand extends AbstractCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Router(TypeOfProceedRequest.REDIRECT, JspPath.PASSWORD_JSP, null);
    }
}
