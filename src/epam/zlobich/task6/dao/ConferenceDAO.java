package epam.zlobich.task6.dao;

import epam.zlobich.task6.entity.entitybd.Conference;
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
import java.util.concurrent.LinkedBlockingQueue;

public class ConferenceDao extends AbstractDao<Integer, Conference> {

    private final static Logger LOGGER = LogManager.getLogger(ConferenceDao.class);
    private final static String FIND_ALL = "SELECT * FROM conference";
    private final static String FIND_BY_ID = "SELECT * FROM conference WHERE idConference=?";
    private final static String ADD_CONFERENCE = "INSERT INTO conference (ConferenceName, Place) VALUES (?, ?)";

    private final static String CHECK_USER = "SELECT * FROM user_has_conference WHERE User_Login=? AND Conference_idConference=?";

    ConferenceDao()
    {

    };
    @Override
    public List<Conference> findAll() throws DaoException {

        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<Conference>list = new ArrayList<>();

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Conference conference = new Conference();
                conference.setId(resultSet.getInt(ColumnNames.CONFERENCE_ID));
                conference.setName(resultSet.getString(ColumnNames.CONFERENCE_NAME));
                conference.setPlace(resultSet.getString(ColumnNames.CONFERENCE_PLACE));

                list.add(conference);
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

    public List<Conference> findNotUserConference(String login) throws DaoException {
        List<Conference> list = new ArrayList<>();
        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;


        try {

            connection = new ProxyConnection();

            for (Conference conference : findAll()
                    ) {
                preparedStatement = connection.prepareStatement(CHECK_USER);
                preparedStatement.setString(1, login);
                preparedStatement.setInt(2, conference.getId());
                resultSet = preparedStatement.executeQuery();

                if(!resultSet.next())
                {
                    list.add(conference);
                }
            }

            return list;
        } catch (SQLException e){
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
    public Conference findEntityById(Integer id) throws DaoException {
        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Conference conference = new Conference();
                conference.setId(id);
                conference.setName(resultSet.getString(ColumnNames.CONFERENCE_NAME));
                conference.setPlace(resultSet.getString(ColumnNames.CONFERENCE_PLACE));

                preparedStatement.close();
                return conference;
            }
            preparedStatement.close();
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

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Conference entity) throws DaoException {

        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(ADD_CONFERENCE);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPlace());

            preparedStatement.execute();

            preparedStatement.close();
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
    public Conference update(Conference entity) {
        throw new UnsupportedOperationException();
    }
}
