package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.Lecture;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.LectureService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class LectureCommand extends AbstractCommand {

    private Boolean addToDatabase = null;

    public LectureCommand(){}
    public LectureCommand(Boolean check)
    {
        addToDatabase = check;
    }

    @Override
    public Router execute(HttpServletRequest request) {

        String themeName = request.getParameter("theme").split(" ")[0];

        try {
            ArrayList<Lecture> listLectures = new LectureService().getLectureByTheme(themeName);

            request.setAttribute("listLectures", listLectures);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.LECTURE_JSP, null);
        } catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.ERROR_JSP, null);
        }
    }
}
