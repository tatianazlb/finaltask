package epam.zlobich.task6.service;

import epam.zlobich.task6.command.AbstractCommand;
import epam.zlobich.task6.dao.ConferenceDao;
import epam.zlobich.task6.dao.FactoryDao;
import epam.zlobich.task6.entity.entitybd.Conference;
import epam.zlobich.task6.entity.entitybd.UserBd;
import epam.zlobich.task6.exception.DaoException;
import epam.zlobich.task6.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class ConferenceService {
    ConferenceDao dao;

    public ConferenceService()
    {
        dao = new FactoryDao().getConferenceDAO();
    }

    public ArrayList<Conference> findUserConferences (UserBd user) throws ServiceException {
        try {


        ArrayList<Conference> conferences = new ArrayList<>();

        for (Integer id:user.getIdConference()
             ) {
            conferences.add(dao.findEntityById(id));
        }
        return conferences;
        }  catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public ArrayList<Conference> findNotUserConference (UserBd userBd) throws ServiceException {
        try {
            return (ArrayList<Conference>) dao.findNotUserConference(userBd.getLogin());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Conference> findAll() throws ServiceException {
        try {
            return dao.findAll();
        }  catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean addConference (Conference conference) throws ServiceException {

        try {
            return dao.create(conference);
        }  catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
