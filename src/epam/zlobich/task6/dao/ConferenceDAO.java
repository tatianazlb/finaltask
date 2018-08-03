package epam.zlobich.task6.dao;

import epam.zlobich.task6.entity.entitybd.Conference;
import epam.zlobich.task6.entity.entitybd.UserBD;
import epam.zlobich.task6.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConferenceDAO extends AbstractDAO<Integer, Conference> {
    @Override
    public List<Conference> findAll() {
        ResultSet rsObj = null;
        Connection connObj = null;
        PreparedStatement pstmtObj = null;
        ConnectionPool pool = new ConnectionPool();

        ArrayList<Conference>list = new ArrayList<>();

        try {
            connObj = pool.retrieve();

            pstmtObj = connObj.prepareStatement("SELECT * FROM conference");
            rsObj = pstmtObj.executeQuery();

            while (rsObj.next()) {
                Conference conference = new Conference();
                conference.setId(rsObj.getInt("idConference"));
                conference.setName(rsObj.getString("ConferenceName"));
                conference.setPlace(rsObj.getString("Place"));

                list.add(conference);
            }

            return list;

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
    public Conference findEntityById(Integer id) {

        ResultSet rsObj = null;
        Connection connObj = null;
        PreparedStatement pstmtObj = null;
        ConnectionPool pool = new ConnectionPool();

        try {
            connObj = pool.retrieve();

            pstmtObj = connObj.prepareStatement("SELECT * FROM conference WHERE idConference='" +id+"'");
            rsObj = pstmtObj.executeQuery();

            while (rsObj.next()) {
                Conference conference = new Conference();
                conference.setId(id);
                conference.setName(rsObj.getString("ConferenceName"));
                conference.setPlace(rsObj.getString("Place"));

                return conference;
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
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Conference entity) {
        return false;
    }

    @Override
    public Conference update(Conference entity) {
        return null;
    }
}
