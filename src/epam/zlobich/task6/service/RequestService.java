package epam.zlobich.task6.service;

import epam.zlobich.task6.dao.FactoryDao;
import epam.zlobich.task6.dao.RequestDao;
import epam.zlobich.task6.entity.entitybd.Request;
import epam.zlobich.task6.exception.DaoException;
import epam.zlobich.task6.exception.ServiceException;

import java.util.ArrayList;

public class RequestService {
    private RequestDao dao;

    public RequestService()
    {
        dao = new FactoryDao().getRequestDAO();
    }

    public boolean addRequest(Request request) throws ServiceException {
        try{
            return dao.create(request);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public ArrayList<Request> findRequestByUser(String user) throws ServiceException {
        try{
            return (ArrayList) dao.findEntityByUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public ArrayList<Request> findAll() throws ServiceException {
        try{
            return (ArrayList) dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean deleteRequest(String title) throws ServiceException {
        try{
            return dao.delete(title);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    public Request findRequestById (String title) throws ServiceException {
        try{
            return dao.findEntityById(title);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }
}
