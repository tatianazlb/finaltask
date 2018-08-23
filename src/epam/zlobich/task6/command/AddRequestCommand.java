package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.Request;
import epam.zlobich.task6.entity.entitybd.UserBd;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.RequestService;

import javax.servlet.http.HttpServletRequest;

public class AddRequestCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) {

        try {
            String themeName = request.getParameter("theme").split(" ")[0];
            Request req = new Request();
            req.setTitle(request.getParameter("title"));
            req.setUser(((UserBd) request.getSession().getAttribute("user")).getLogin());
            req.setThemeName(themeName);

            return new Router(TypeOfProceedRequest.FORWARD, JspPath.LECTURE_JSP , new LectureCommand(new RequestService().addRequest(req)));
        } catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.ERROR_JSP, null);
        }
    }
}
