package epam.zlobich.task6.dao;
import epam.zlobich.task6.exception.DaoException;
import epam.zlobich.task6.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public interface AbstractDao<K, T> {
    List<T> findAll() throws DaoException;
    T findEntityById(K id) throws  DaoException;
    boolean delete(K id) throws  DaoException;
    boolean create(T entity) throws  DaoException;
    T update(T entity) throws DaoException;

}
