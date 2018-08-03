package epam.zlobich.task6.dao;

import epam.zlobich.task6.entity.entitybd.Conference;
import epam.zlobich.task6.entity.entitybd.Theme;
import epam.zlobich.task6.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThemeDAO extends AbstractDAO<String, Theme> {
    @Override
    public List<Theme> findAll() {
        return null;
    }

    @Override
    public Theme findEntityById(String id) {
        return null;
    }

    public List<Theme> findByConference(Integer idConf) {

        ResultSet rsObj = null;
        Connection connObj = null;
        PreparedStatement pstmtObj = null;
        ConnectionPool pool = new ConnectionPool();
        ArrayList<Theme> list = new ArrayList<>();

        try {
            connObj = pool.retrieve();

            pstmtObj = connObj.prepareStatement("SELECT * FROM theme WHERE Conference_idConference='" +idConf+"'");
            rsObj = pstmtObj.executeQuery();

            while (rsObj.next()) {

                Theme theme = new Theme();
                theme.setName(rsObj.getString("Name"));
                theme.setIdConference(idConf);
                theme.setDate(rsObj.getDate("Date"));

                list.add(theme);
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
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean create(Theme entity) {
        return false;
    }

    @Override
    public Theme update(Theme entity) {
        return null;
    }
}
