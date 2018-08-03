package epam.zlobich.task6.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public abstract class AbstractDAO <K, T> {

    public abstract List<T> findAll();
    public abstract T findEntityById(K id);
    public abstract boolean delete(K id);
    public abstract boolean create(T entity);
    public abstract T update(T entity);
}
