package epam.zlobich.task6.command;

import epam.zlobich.task6.router.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractCommand {

    public abstract Router execute(HttpServletRequest request) ;

}
