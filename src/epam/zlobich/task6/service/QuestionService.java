package epam.zlobich.task6.service;

import epam.zlobich.task6.dao.FactoryDao;
import epam.zlobich.task6.dao.QuestionDao;
import epam.zlobich.task6.entity.entitybd.Question;
import epam.zlobich.task6.exception.DaoException;
import epam.zlobich.task6.exception.ServiceException;

import java.util.ArrayList;

public class QuestionService {

    private QuestionDao dao;

    public QuestionService(){
        dao = new FactoryDao().getQuestionDao();
    }

    public ArrayList<Question> findQuestionsByUser(String login) throws ServiceException {
        try {
            return (ArrayList<Question>) dao.findEntityByUser(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public ArrayList<Question> findAll() throws ServiceException {
        try {
            return (ArrayList<Question>) dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean addQuestion(Question question) throws ServiceException {
        try{
            return dao.create(question);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Question findQuestionById(Integer id) throws ServiceException {
        try {
            return dao.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Question answerQuestion(Question question) throws ServiceException {
        try {
            return dao.update(question);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    public boolean deleteQuestion(Integer id) throws ServiceException {
        try{
            return dao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
