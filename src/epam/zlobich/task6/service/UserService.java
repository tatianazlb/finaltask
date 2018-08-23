package epam.zlobich.task6.service;

import epam.zlobich.task6.dao.FactoryDao;
import epam.zlobich.task6.dao.UserDao;
import epam.zlobich.task6.entity.entitybd.UserBd;
import epam.zlobich.task6.exception.DaoException;
import epam.zlobich.task6.exception.ServiceException;

public class UserService {
    private UserDao dao;

    public UserService()
    {
        dao = new FactoryDao().getUserDAO();
    }

    public UserBd checkUser (String login, String pass) throws ServiceException {
        try {
            return dao.findAndCheck(login, pass);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean addUser(UserBd user, String pass) throws ServiceException {
        try{
            return dao.create(user, pass);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean deleteUser(UserBd userBd) throws ServiceException {
        try {
            return dao.delete(userBd.getLogin());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    public UserBd updateUser(UserBd userBd) throws ServiceException {
        try {
            return dao.update(userBd);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    public boolean addUserConference(String login, int idConference) throws ServiceException {
        try {
            return dao.createUserToConference(login, idConference);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
