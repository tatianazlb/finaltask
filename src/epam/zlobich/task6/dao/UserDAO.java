package epam.zlobich.task6.dao;

import epam.zlobich.task6.entity.entitybd.UserBD;
import epam.zlobich.task6.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends AbstractDAO<String, UserBD> {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/conferencedbschema";

    static final String JDBC_USER = "root";
    static final String JDBC_PASS = "root";


    @Override
    public List<UserBD> findAll() {
        return null;
    }

    @Override
    public UserBD findEntityById(String id) {

        ResultSet rsObj = null;
        Connection connObj = null;
        PreparedStatement pstmtObj = null;
        ConnectionPool pool = new ConnectionPool();

        try {
            connObj = pool.retrieve();

            pstmtObj = connObj.prepareStatement("SELECT * FROM user WHERE Login='" +id+"'");
            rsObj = pstmtObj.executeQuery();

            while (rsObj.next()) {
                    UserBD user = new UserBD();
                    user.setLogin(id);
                    user.setRole(rsObj.getBoolean("AdminCheck"));
                    user.setHomeland(rsObj.getString("Homeland"));
                    return user;
            }
            return null;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            pool.putback(connObj);
        }
        return null;
    }


    public UserBD findAndCheck(String id, String p)
    {
        ResultSet rsObj = null;
        Connection connObj = null;
        PreparedStatement pstmtObj = null;
        ConnectionPool pool = new ConnectionPool();

        try {
            connObj = pool.retrieve();

            pstmtObj = connObj.prepareStatement("SELECT * FROM user WHERE Login='" +id+"'");
            rsObj = pstmtObj.executeQuery();

            while (rsObj.next()) {
                if (rsObj.getString("Password").equals(p)) {
                    UserBD user = new UserBD();
                    user.setLogin(id);
                    user.setRole(rsObj.getBoolean("AdminCheck"));
                    user.setHomeland(rsObj.getString("Homeland"));

                    PreparedStatement statement = connObj.prepareStatement("SELECT * FROM user_has_conference WHERE User_Login='" +id+"'");
                    ResultSet result= statement.executeQuery();

                    while (result.next())
                    {
                        user.getIdConference().add(result.getInt("Conference_idConference"));
                    }
                    return user;
                }
            }
            return null;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            pool.putback(connObj);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean create(UserBD entity) {
        return false;
    }

    @Override
    public UserBD update(UserBD entity) {
        return null;
    }
}
