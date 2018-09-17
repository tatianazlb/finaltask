package epam.zlobich.task6.dao;

import epam.zlobich.task6.entity.entitybd.Request;
import epam.zlobich.task6.exception.DaoException;
import epam.zlobich.task6.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDao implements AbstractDao<String, Request> {


    private final static Logger LOGGER = LogManager.getLogger(RequestDao.class);

    private final static String ADD_REQUEST = "INSERT INTO request (TitleRequest, User_Login, Theme_Name) VALUES (?, ?, ?)";
    private final static String FIND_BY_USER = "SELECT * FROM request WHERE User_Login=?";

    private final static String FIND_BY_ID = "SELECT * FROM request WHERE TitleRequest=?";
    private final static String FIND_ALL = "SELECT * FROM request";


    private final static String DELETE_REQUEST = "DELETE FROM request WHERE TitleRequest=?";

    RequestDao()
    {

    };
    @Override
    public List<Request> findAll() throws DaoException {
        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<Request> list = new ArrayList<>();

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Request request = new Request();
                request.setThemeName(resultSet.getString(ColumnNames.REQUEST_THEME));
                request.setTitle(resultSet.getString(ColumnNames.REQUEST_TITLE));
                request.setUser(resultSet.getString(ColumnNames.REQUEST_USER));
                list.add(request);
            }
            return list;

        }
        catch (SQLException e){
            throw new DaoException(e);
        } finally {
            if (preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.INFO, e.getMessage());
                }
            }
            if(connection!=null) {
                connection.close();
            }
        }
    }

    @Override
    public Request findEntityById(String id) throws DaoException {
        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<Request> list = new ArrayList<>();

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Request request = new Request();
                request.setThemeName(resultSet.getString(ColumnNames.REQUEST_THEME));
                request.setTitle(resultSet.getString(ColumnNames.REQUEST_TITLE));
                request.setUser(resultSet.getString(ColumnNames.REQUEST_USER));
                return request;
            }
        }
        catch (SQLException e){
            throw new DaoException(e);
        } finally {
            if (preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.INFO, e.getMessage());
                }
            }
            if(connection!=null) {
                connection.close();
            }
        }
        return null;
    }


    public List<Request> findEntityByUser(String login) throws DaoException {
        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<Request> list = new ArrayList<>();

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_BY_USER);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Request request = new Request();
                request.setThemeName(resultSet.getString(ColumnNames.REQUEST_THEME));
                request.setTitle(resultSet.getString(ColumnNames.REQUEST_TITLE));
                request.setUser(login);
                list.add(request);
            }
            return list;

        }
        catch (SQLException e){
            throw new DaoException(e);
        } finally {
            if (preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.INFO, e.getMessage());
                }
            }
            if(connection!=null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean delete(String id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(DELETE_REQUEST);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            return true;

        }
        catch (SQLException e){
            throw new DaoException(e);
        } finally {
            if (preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.INFO, e.getMessage());
                }
            }
            if(connection!=null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean create(Request entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(ADD_REQUEST);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getUser());
            preparedStatement.setString(3, entity.getThemeName());
            preparedStatement.execute();

            return true;

        }
        catch (SQLException e){
            throw new DaoException(e);
        } finally {
            if (preparedStatement!=null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.INFO, e.getMessage());
                }
            }
            if(connection!=null) {
                connection.close();
            }
        }
    }

    @Override
    public Request update(Request entity) {
        throw new UnsupportedOperationException();
    }
}
