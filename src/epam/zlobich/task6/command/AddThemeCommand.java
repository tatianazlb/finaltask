package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.Theme;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.ThemeService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddThemeCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) {

        try {
            Theme theme = new Theme();

            theme.setName(request.getParameter("name"));
            theme.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")));

            theme.setIdConference(Integer.valueOf(request.getParameter("idConf").split(" ")[0]));

            return new Router(TypeOfProceedRequest.FORWARD, JspPath.THEME_JSP, new ThemeCommand(new ThemeService().addTheme(theme)));

        } catch (ServiceException | ParseException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.ERROR_JSP, null);
        }

    }
}
