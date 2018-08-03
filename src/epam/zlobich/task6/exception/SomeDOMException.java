package epam.zlobich.task6.exception;

public class SomeDOMException extends Exception{

    SomeDOMException()
    {
        super();
    }
    SomeDOMException(String m)
    {
        super(m);
    }
    public SomeDOMException(Exception e)
    {
        super(e);
    }
}
