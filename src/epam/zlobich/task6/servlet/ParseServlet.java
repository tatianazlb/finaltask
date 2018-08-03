package epam.zlobich.task6.servlet;

import epam.zlobich.task6.dombuilder.StoneDOMBuilder;
import epam.zlobich.task6.entity.Gem;
import epam.zlobich.task6.entity.Golem;
import epam.zlobich.task6.entity.Stone;
import epam.zlobich.task6.exception.SomeDOMException;
import epam.zlobich.task6.exception.SomeStAXException;
import epam.zlobich.task6.exception.ValidationException;
import epam.zlobich.task6.staxparser.ParserStAX;
import epam.zlobich.task6.validator.ValidatorSAX;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "/parseServlet")
public class ParseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String submButton = request.getParameter("submbutton");

        String tableGem = "<br> <table border=\"1\"><caption>This is table by "+ submButton +
                "</caption>\n" +
                "   <tr><th>Name</th><th>Weight</th><th>Country</th><th>Price</th></tr>";
        String tableGolem = "<table border=\"1\"><caption>This is table too by "+submButton+"</caption>\n" +
                "   <tr><th>Name</th><th>Weight</th><th>Country</th><th>Element</th><th>Owner</th></tr>";
        ArrayList<Stone> stones=null;


        try {

            switch (submButton) {
                case "DOM": {
                    StoneDOMBuilder builder = new StoneDOMBuilder();
                    builder.buildSetStones(request.getServletContext().getRealPath("stone.xml"));
                    stones = builder.getStones();
                    break;
                }
                case "SAX": {
                    ValidatorSAX validatorSAX = new ValidatorSAX();
                    validatorSAX.validate(request.getServletContext().getRealPath("stone.xml"),
                            request.getServletContext().getRealPath("stone.xsd"));
                    stones = validatorSAX.getHandler().getStones();
                    break;
                }
                case "STAX": {
                    ParserStAX staxBuilder = new ParserStAX();
                    staxBuilder.buildSetStones(request.getServletContext().getRealPath("stone.xml"));
                    stones = staxBuilder.getStones();
                    break;
                }
            }

            String s = request.getServletContext().getRealPath("stone.xml");

            for (Stone stone : stones
                    ) {
                if (stone.getClass() == Gem.class) {
                    tableGem += stone.toTableString();
                } else if (stone.getClass() == Golem.class) {
                    tableGolem += stone.toTableString();
                }
            }
            tableGem += "</table>";
            tableGolem += "</table>";

            request.setAttribute("res", tableGem + "<br>" + tableGolem);
        }
        catch (NullPointerException |SomeStAXException | ValidationException |SomeDOMException e)
        {
            request.setAttribute("res", "Sorry, there is something wrong: "+e.getMessage());

        }
        request.getRequestDispatcher("table.jsp").forward(request, response);
    }
}
