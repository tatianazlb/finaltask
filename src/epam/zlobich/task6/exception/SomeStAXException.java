package epam.zlobich.task6.exception;

public class SomeStAXException extends Exception{

    SomeStAXException()
    {
        super();
    }
    public SomeStAXException(Exception e)
    {
        super(e);
    }
    SomeStAXException(String m)
    {
        super(m);
    }
}
