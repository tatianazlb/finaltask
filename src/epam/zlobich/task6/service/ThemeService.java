package epam.zlobich.task6.service;

import epam.zlobich.task6.dao.FactoryDao;
import epam.zlobich.task6.dao.ThemeDao;
import epam.zlobich.task6.entity.entitybd.Theme;
import epam.zlobich.task6.exception.DaoException;
import epam.zlobich.task6.exception.ServiceException;

import java.util.ArrayList;

public class ThemeService {

    private ThemeDao dao;

    public ThemeService()
    {
        dao = new FactoryDao().getThemeDAO();
    }

    public ArrayList<Theme> getThemesByConferenceID(Integer id) throws ServiceException {
        try{
            return (ArrayList) dao.findByConference(id);
        }catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean addTheme(Theme theme) throws ServiceException {
        try{
            return dao.create(theme);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
