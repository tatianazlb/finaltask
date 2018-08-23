package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.Theme;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.ThemeService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ThemeCommand extends AbstractCommand {

    private Boolean addToDatabase = null;

    public ThemeCommand(){}
    public ThemeCommand(Boolean check)
    {
        addToDatabase = check;
    }

    @Override
    public Router execute(HttpServletRequest request) {

        try {
            Integer conferenceId = Integer.valueOf(request.getParameter("idConf").split(" ")[0]);

            ArrayList<Theme> listOfThemes = new ThemeService().getThemesByConferenceID(conferenceId);

            request.setAttribute("listOfThemes", listOfThemes);

            return new Router(TypeOfProceedRequest.FORWARD, JspPath.THEME_JSP, null);
        }
        catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.ERROR_JSP, null);
        }
    }
}
