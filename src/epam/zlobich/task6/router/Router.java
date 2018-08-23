package epam.zlobich.task6.router;

import epam.zlobich.task6.command.AbstractCommand;

public class Router {

    TypeOfProceedRequest type;
    String jspAdress;

    AbstractCommand nextCommand;

    public Router(TypeOfProceedRequest type, String jspAdress, AbstractCommand command)
    {
        this.jspAdress = jspAdress;
        this.type = type;
        this.nextCommand = command;
    }

    public String getJspAdress() {
        return jspAdress;
    }

    public TypeOfProceedRequest getType() {
        return type;
    }

    public AbstractCommand getNextCommand() {
        return nextCommand;
    }
}
