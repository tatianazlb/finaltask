package epam.zlobich.task6.exception;

/**
 * Created by UserBD on 25-Jun-18.
 */
public class ValidationException extends Exception {

    ValidationException()
    {
        super();
    }
    public ValidationException(Exception e)
    {
        super(e);
    }
}
