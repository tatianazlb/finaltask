package epam.zlobich.task6.dao;
import epam.zlobich.task6.exception.DaoException;
import epam.zlobich.task6.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public abstract class AbstractDao<K, T> {


    ConnectionPool pool = ConnectionPool.getInstance();

    public abstract List<T> findAll() throws DaoException;
    public abstract T findEntityById(K id) throws  DaoException;
    public abstract boolean delete(K id) throws  DaoException;
    public abstract boolean create(T entity) throws  DaoException;
    public abstract T update(T entity) throws DaoException;
}
