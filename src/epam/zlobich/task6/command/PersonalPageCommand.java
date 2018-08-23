package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.*;
import epam.zlobich.task6.entity.entitybd.UserBd;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.LectureService;
import epam.zlobich.task6.service.QuestionService;
import epam.zlobich.task6.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class PersonalPageCommand extends AbstractCommand {

    public PersonalPageCommand(){}

    public PersonalPageCommand(Boolean check)
    {
        addToDatabase=check;
    }

    private Boolean addToDatabase = null;
    @Override
    public Router execute(HttpServletRequest request)  {
        try {

            if (!((UserBd) request.getSession().getAttribute("user")).isRole()) {

                ArrayList<Lecture> listOfUserLectures = new LectureService().getLectureByUser(((UserBd) request.getSession().getAttribute("user")).getLogin());
                ArrayList<Request> listOfUserRequests = new RequestService().findRequestByUser(((UserBd) request.getSession().getAttribute("user")).getLogin());
                ArrayList<Question> listOfUserQuestions = new QuestionService().findQuestionsByUser(((UserBd) request.getSession().getAttribute("user")).getLogin());

                request.setAttribute("listOfUserLectures", listOfUserLectures);
                request.setAttribute("listOfUserRequests", listOfUserRequests);
                request.setAttribute("listOfUserQuestions", listOfUserQuestions);

            } else {
                ArrayList<Request> listOfRequests = new RequestService().findAll();
                ArrayList<Question> listOfQuestions = new QuestionService().findAll();

                request.setAttribute("listOfRequests", listOfRequests);
                request.setAttribute("listOfQuestions", listOfQuestions);
            }

            return new Router(TypeOfProceedRequest.FORWARD, JspPath.PERSONAL_PAGE_JSP, null);

        } catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD,JspPath.ERROR_JSP, null);
        }

    }
}
