package epam.zlobich.task6.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {

    private LinkedBlockingDeque<Connection> availableConns = new LinkedBlockingDeque<Connection>();
    private LinkedList<Connection> usedConns = new LinkedList<>();
    private String url = "jdbc:mysql://localhost:3306/conferencebdschema";
    private String driver = "com.mysql.jdbc.Driver";

    static final String JDBC_USER = "root";
    static final String JDBC_PASS = "root";

    private int initConnCnt = 10;

    public ConnectionPool() {
        for (int i = 0; i < initConnCnt; i++) {
            availableConns.push(getConnection());
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(url, JDBC_USER, JDBC_PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Connection retrieve() throws SQLException {
        Connection newConn = null;
        if (availableConns.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = (Connection) availableConns.peek();
            availableConns.remove(newConn);
        }
        usedConns.add(newConn);
        return newConn;
    }

    public void putback(Connection c) throws NullPointerException {
        if (c != null) {
            if (usedConns.remove(c)) {
                availableConns.push(c);
            } else {
                throw new NullPointerException("Connection not in the usedConns array");
            }
        }
    }

    public int getAvailableConnsCnt() {
        return availableConns.size();
    }
}