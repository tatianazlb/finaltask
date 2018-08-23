package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.Conference;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;

public class AddConferenceCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) {

        try {

        Conference conference = new Conference();
        conference.setPlace(request.getParameter("place"));
        conference.setName(request.getParameter("name"));

        return new Router(TypeOfProceedRequest.FORWARD, JspPath.CONFERENCE_JSP, new ConferenceCommand(new ConferenceService().addConference(conference)));

        } catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.ERROR_JSP, null);
        }
    }
}
