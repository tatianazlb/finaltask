package epam.zlobich.task6.exception;

public class DaoException extends Exception {
    public DaoException(Exception e)
    {
        super(e);
    }
    public DaoException(String message)
    {
        super(message);
    }
    public DaoException()
    {
        super();
    }
}
