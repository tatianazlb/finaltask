package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.UserBd;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegisterForConferenceCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) {

        try {
            Integer conferenceId = Integer.valueOf(request.getParameter("idConf").split(" ")[0]);
            ((UserBd) request.getSession().getAttribute("user")).getIdConference().add(conferenceId);

            return new Router(TypeOfProceedRequest.REDIRECT, JspPath.CONFERENCE_JSP,
                    new ConferenceCommand(new UserService().addUserConference(((UserBd) request.getSession().getAttribute("user")).getLogin(), conferenceId)));

        } catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.ERROR_JSP, null);
        }
    }
}
