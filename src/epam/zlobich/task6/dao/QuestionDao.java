package epam.zlobich.task6.dao;

import epam.zlobich.task6.entity.entitybd.Question;
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

public class QuestionDao extends AbstractDao<Integer, Question> {
    private final static Logger LOGGER = LogManager.getLogger(QuestionDao.class);

    private final static String FIND_BY_USER = "SELECT * FROM question WHERE User_Login=?";
    private final static String FIND_BY_ID = "SELECT * FROM question WHERE idQuestion=?";

    private final static String FIND_ALL = "SELECT * FROM question";

    private final static String ADD_QUESTION = "INSERT INTO question (AskedQuestion, User_Login, Answer) VALUES (?, ?, ?)";

    private final static String UPDATE_QUESTION = "UPDATE question SET Answer = ? WHERE idQuestion = ?";
    private final static String DELETE_QUESTION = "DELETE FROM question WHERE idQuestion=?";

    @Override
    public List<Question> findAll() throws DaoException {
        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Question> list = new ArrayList<>();

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt(ColumnNames.QUESTION_ID));

                question.setAskedQuestion(resultSet.getString(ColumnNames.QUESTION_ASKED));

                question.setAnswer(resultSet.getString(ColumnNames.QUESTION_ANSWER));

                question.setUser(resultSet.getString(ColumnNames.QUESTION_USER));
                list.add(question);
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
    public Question findEntityById(Integer id) throws DaoException {
        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Question> list = new ArrayList<>();

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt(ColumnNames.QUESTION_ID));

                question.setAskedQuestion(resultSet.getString(ColumnNames.QUESTION_ASKED));

                question.setAnswer(resultSet.getString(ColumnNames.QUESTION_ANSWER));

                question.setUser(resultSet.getString(ColumnNames.QUESTION_USER));
                return question;
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

    public List<Question> findEntityByUser(String login) throws DaoException {
        ResultSet resultSet = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Question> list = new ArrayList<>();

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(FIND_BY_USER);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt(ColumnNames.QUESTION_ID));

                question.setAskedQuestion(resultSet.getString(ColumnNames.QUESTION_ASKED));

                question.setAnswer(resultSet.getString(ColumnNames.QUESTION_ANSWER));

                question.setUser(login);
                list.add(question);
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
    public boolean delete(Integer id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(DELETE_QUESTION);
            preparedStatement.setInt(1, id);
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
    public boolean create(Question entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(ADD_QUESTION);
            preparedStatement.setString(1, entity.getAskedQuestion());
            preparedStatement.setString(2, entity.getUser());
            preparedStatement.setString(3, entity.getAnswer());

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
    public Question update(Question entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new ProxyConnection();

            preparedStatement = connection.prepareStatement(UPDATE_QUESTION);
            preparedStatement.setString(1, entity.getAnswer());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.execute();
            return entity;
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
