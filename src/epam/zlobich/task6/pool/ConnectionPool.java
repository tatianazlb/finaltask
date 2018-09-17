package epam.zlobich.task6.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;


public class ConnectionPool {

    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private int initConnCnt = 10;
    private LinkedBlockingQueue<Connection> availableConns = new LinkedBlockingQueue<>(initConnCnt);
    private ArrayList<Connection> usedConns = new ArrayList<>();


    private static Properties properties = new Properties();

    private static final ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance()
    {
        return instance;
    }

    private ConnectionPool() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            properties = PropertyClass.getProperties();

            for (int i = 0; i < initConnCnt; i++) {
                availableConns.offer(getConnection());
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }

    private Connection getConnection()  {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
        return conn;
    }

    public Connection retrieve(){
        Connection newConn = null;
        try {
            newConn = availableConns.take();
            usedConns.add(newConn);
        } catch (InterruptedException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
        return newConn;
    }

    public void putback(Connection c) {
        try {
            if (c != null) {
                usedConns.remove(c);
                availableConns.put(c);
            }
        } catch (InterruptedException e) {

            LOGGER.log(Level.INFO, e.getMessage());
        }
    }

    public int getAvailableConnsCnt() {
        return availableConns.size();
    }

}