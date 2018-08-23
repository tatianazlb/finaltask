package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.Question;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

public class AnswerQuestionCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) {

        try {
            Question question = new QuestionService().findQuestionById(Integer.valueOf(request.getParameter("idQuestion")));

            question.setAnswer(request.getParameter("answer"));

            new QuestionService().answerQuestion(question);

            return new Router(TypeOfProceedRequest.REDIRECT, JspPath.PERSONAL_PAGE_JSP, new PersonalPageCommand());

        } catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD,JspPath.ERROR_JSP, null);
        }
    }
}
