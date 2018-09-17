package epam.zlobich.task6.dao;

import epam.zlobich.task6.entity.entitybd.Theme;
import epam.zlobich.task6.exception.DaoException;
import epam.zlobich.task6.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThemeDao implements AbstractDao<String, Theme> {


    private final static Logger LOGGER = LogManager.getLogger(ThemeDao.class);

    private final static String FIND_BY_CONFERENCE = "SELECT * FROM theme WHERE Conference_idConference=?";
    private final static String ADD_THEME = "INSERT INTO theme (Name, Date, Conference_idConference) VALUES (?, ?, ?)";


    ThemeDao()
    {};
    @Override
    public List<Theme> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Theme findEntityById(String id) {
        throw new UnsupportedOperationException();
    }

    public List<Theme> findByConference(Integer idConf) throws DaoException {

        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<Theme> list = new ArrayList<>();

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_BY_CONFERENCE);
            preparedStatement.setInt(1, idConf);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Theme theme = new Theme();
                theme.setName(resultSet.getString(ColumnNames.THEME_NAME));
                theme.setIdConference(idConf);
                theme.setDate(resultSet.getDate(ColumnNames.THEME_DATE));

                list.add(theme);
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
    public boolean delete(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Theme entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            if (findEntityById(entity.getName())!=null) return false;
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(ADD_THEME);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDate(2, new java.sql.Date(entity.getDate().getTime()));
            preparedStatement.setInt(3, entity.getIdConference());

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
    public Theme update(Theme entity) {
        throw new UnsupportedOperationException();
    }
}
