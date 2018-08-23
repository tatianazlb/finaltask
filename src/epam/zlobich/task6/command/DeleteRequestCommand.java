package epam.zlobich.task6.command;

import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.RequestService;

import javax.servlet.http.HttpServletRequest;

public class DeleteRequestCommand extends AbstractCommand {

    @Override
    public Router execute(HttpServletRequest request) {

        try {

            new RequestService().deleteRequest(request.getParameter("title"));
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.PERSONAL_PAGE_JSP, new PersonalPageCommand());

        }catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.ERROR_JSP, null);
        }
    }
}
