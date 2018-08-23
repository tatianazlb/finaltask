package epam.zlobich.task6.dao;

import epam.zlobich.task6.entity.entitybd.Lecture;
import epam.zlobich.task6.exception.DaoException;
import epam.zlobich.task6.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LectureDao extends AbstractDao<String, Lecture> {


    private final static Logger LOGGER = LogManager.getLogger(LectureDao.class);


    private final static String FIND_BY_THEME = "SELECT * FROM lecture WHERE Theme_Name=?";
    private final static String FIND_BY_USER = "SELECT * FROM lecture WHERE User_Login=?";
    private final static String FIND_BY_ID = "SELECT * FROM lecture WHERE Title=?";

    private final static String ADD_LECTURE = "INSERT INTO lecture (Title, User_Login, Theme_Name) VALUES (?, ?, ?)";

    private final static String DELETE_LECTURE = "DELETE FROM lecture WHERE Title=?";


    LectureDao()
    {

    }
    @Override
    public List<Lecture> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Lecture findEntityById(String id) throws DaoException {
        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Lecture lecture = new Lecture();
                lecture.setThemeName(ColumnNames.LECTURE_THEME);
                lecture.setTitle(resultSet.getString(ColumnNames.LECTURE_TITLE));
                lecture.setUser(resultSet.getString(ColumnNames.LECTURE_USER));

                return lecture;
            }

            return null;
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

    public List<Lecture> findByUserName(String login) throws DaoException {
        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<Lecture> list = new ArrayList<>();

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_BY_USER);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                Lecture lecture = new Lecture();
                lecture.setThemeName(resultSet.getString(ColumnNames.LECTURE_THEME));
                lecture.setTitle(resultSet.getString(ColumnNames.LECTURE_TITLE));
                lecture.setUser(login);
                list.add(lecture);
            }

            preparedStatement.close();
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

    public List<Lecture> findByThemeName(String name) throws DaoException {

        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<Lecture> list = new ArrayList<>();

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_BY_THEME);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();



            while (resultSet.next()) {

                Lecture lecture = new Lecture();
                lecture.setThemeName(name);
                lecture.setTitle(resultSet.getString(ColumnNames.LECTURE_TITLE));
                lecture.setUser(resultSet.getString(ColumnNames.LECTURE_USER));
                list.add(lecture);
            }

            preparedStatement.close();
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

            preparedStatement = connection.prepareStatement(DELETE_LECTURE);
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
    public boolean create(Lecture entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            if (findEntityById(entity.getTitle())!=null) return false;

            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(ADD_LECTURE);
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
    public Lecture update(Lecture entity) {
        throw new UnsupportedOperationException();
    }
}
