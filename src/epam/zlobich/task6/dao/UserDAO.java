package epam.zlobich.task6.dao;

import epam.zlobich.task6.entity.entitybd.UserBd;
import epam.zlobich.task6.exception.DaoException;
import epam.zlobich.task6.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class UserDao extends AbstractDao<String, UserBd> {

    private final static Logger LOGGER = LogManager.getLogger(UserDao.class);

    private final static String FIND_ALL =  "SELECT * FROM user";
    private final static String FIND_BY_ID = "SELECT * FROM user WHERE Login=?";
    private final static String ADD_USER = "INSERT INTO user (Login, Password, AdminCheck, Homeland) VALUES (?, ?, 0, ?)";
    private final static String DELETE_USER = "DELETE FROM user WHERE Login=?";

    private final static String UPDATE_USER = "UPDATE user SET Homeland = ?, Avatar = ? WHERE Login = ?";

    private final static String FIND_USER_CONFERENCES = "SELECT * FROM user_has_conference WHERE User_Login=?";

    private final static String DELETE_USER_CONFERENCES = "DELETE * FROM user_has_conference WHERE User_Login=?";
    private final static String ADD_USER_CONFERENCE = "INSERT INTO user_has_conference (User_Login, Conference_idConference) VALUES (?, ?)";

    UserDao() {};

    @Override
    public List<UserBd> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserBd findEntityById(String id) throws DaoException {

        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                UserBd user = new UserBd();
                user.setLogin(id);
                user.setRole(resultSet.getBoolean(ColumnNames.USER_ADMIN));
                user.setHomeland(resultSet.getString(ColumnNames.USER_HOMELAND));
                user.setAvatarByBlob(resultSet.getBlob(ColumnNames.USER_AVATAR));
                return user;
            }

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
        return null;
    }


    public UserBd findAndCheck(String id, String p) throws DaoException {

        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString(ColumnNames.USER_PASSWORD).equals(p)) {
                    UserBd user = new UserBd();
                    user.setLogin(id);
                    user.setRole(resultSet.getBoolean(ColumnNames.USER_ADMIN));
                    user.setHomeland(resultSet.getString(ColumnNames.USER_HOMELAND));

                    user.setAvatarByBlob(resultSet.getBlob(ColumnNames.USER_AVATAR));

                    PreparedStatement statement = connection.prepareStatement(FIND_USER_CONFERENCES);
                    statement.setString(1, id);
                    ResultSet result= statement.executeQuery();

                    while (result.next())
                    {
                        user.getIdConference().add(result.getInt(ColumnNames.USER_CONFERENCE));
                    }
                    return user;
                }
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

    @Override
    public boolean delete(String id) throws DaoException {

        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setString(1, id);
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(DELETE_USER_CONFERENCES);
            preparedStatement.setString(1,id);
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
    public boolean create(UserBd entity) { throw new UnsupportedOperationException(); }

    public boolean create(UserBd entity, String password) throws DaoException {

        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            if (findEntityById(entity.getLogin())!=null) return false;
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(3, entity.getHomeland());
            preparedStatement.setString(2, password);

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
    public UserBd update(UserBd entity) throws DaoException {

        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {

            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, entity.getHomeland());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(entity.getAvatar(), "jpg", baos);
            Blob blFile = new javax.sql.rowset.serial.SerialBlob(baos.toByteArray());

            preparedStatement.setBlob(2, blFile );
            preparedStatement.setString(3, entity.getLogin());
            preparedStatement.execute();

            return entity;
        }
        catch (SQLException | IOException e){
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

    public boolean createUserToConference(String login, int idConference) throws DaoException {

        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(ADD_USER_CONFERENCE);

            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, idConference);

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
}
