package epam.zlobich.task6.service;

import epam.zlobich.task6.dao.FactoryDao;
import epam.zlobich.task6.dao.LectureDao;
import epam.zlobich.task6.entity.entitybd.Lecture;
import epam.zlobich.task6.exception.DaoException;
import epam.zlobich.task6.exception.ServiceException;

import java.util.ArrayList;

public class LectureService {

    LectureDao dao;

    public LectureService()
    {
        dao = new FactoryDao().getLectureDAO();
    }

    public ArrayList<Lecture> getLectureByTheme(String name) throws ServiceException {
        try{
            return (ArrayList) dao.findByThemeName(name);
        }  catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public ArrayList<Lecture> getLectureByUser(String user) throws ServiceException {
        try {
            return (ArrayList) dao.findByUserName(user);
        }  catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    public boolean addLecture (Lecture lecture) throws ServiceException {
        try {
            return dao.create(lecture);
        }  catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean deleteLecture(String title) throws ServiceException {
        try {
            return dao.delete(title);
        }  catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
