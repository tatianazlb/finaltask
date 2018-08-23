package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.Conference;
import epam.zlobich.task6.entity.entitybd.UserBd;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ConferenceCommand extends AbstractCommand
{
    private Boolean addToDatabase = null;

    public ConferenceCommand(){};
    public ConferenceCommand(Boolean check)
    {
        addToDatabase = check;
    }

    @Override
    public Router execute(HttpServletRequest request) {

        UserBd currUser = (UserBd)request.getSession().getAttribute("user");

        try {
            if (!currUser.isRole()) {
                ArrayList<Conference> userConferences = new ConferenceService().findUserConferences(currUser);

                ArrayList<Conference> notUserConferences = new ConferenceService().findNotUserConference(currUser);

                request.setAttribute("notUserConference", notUserConferences);
                request.setAttribute("userConference", userConferences);
            } else if (currUser.isRole()) {

                ArrayList<Conference> allConferences = (ArrayList) new ConferenceService().findAll();
                request.setAttribute("allConferences", allConferences);
            }
            request.setAttribute("name", "Hello, " + currUser.getLogin() + "!");

            return new Router(TypeOfProceedRequest.FORWARD, JspPath.CONFERENCE_JSP, null);

        } catch (ServiceException e) {

            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD,JspPath.ERROR_JSP, null);
        }
    }
}
