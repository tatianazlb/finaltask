package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.Lecture;
import epam.zlobich.task6.entity.entitybd.Request;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.LectureService;
import epam.zlobich.task6.service.RequestService;

import javax.servlet.http.HttpServletRequest;

public class ApproveRequestCommand extends AbstractCommand {

    @Override
    public Router execute(HttpServletRequest request) {

        try {
            Request requestBd = new RequestService().findRequestById(request.getParameter("title"));

            Lecture lecture = new Lecture(requestBd);

            Boolean check = new LectureService().addLecture(lecture);
            if (check) {
                new RequestService().deleteRequest(requestBd.getTitle());
            }

            return new Router(TypeOfProceedRequest.FORWARD, JspPath.PERSONAL_PAGE_JSP, new PersonalPageCommand(check));
        } catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.ERROR_JSP, null);
        }
    }
}
