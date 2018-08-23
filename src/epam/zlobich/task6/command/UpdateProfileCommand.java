package epam.zlobich.task6.command;

import epam.zlobich.task6.entity.entitybd.UserBd;
import epam.zlobich.task6.exception.ServiceException;
import epam.zlobich.task6.router.Router;
import epam.zlobich.task6.router.TypeOfProceedRequest;
import epam.zlobich.task6.service.UserService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@MultipartConfig
public class UpdateProfileCommand extends AbstractCommand {
    @Override
    public Router execute(HttpServletRequest request) {

        try {
            UserBd user = (UserBd) request.getSession().getAttribute("user");

            user.setHomeland(request.getParameter("homeland"));
            String s = request.getParameter("avatar");
                try {

                    Part filePart = request.getPart("avatar"); // Retrieves <input type="file" name="file">
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                    InputStream fileContent = filePart.getInputStream();

                    user.setAvatar(ImageIO.read(fileContent));

                } catch (ServletException | IOException e) {
                    request.setAttribute("wrongAvatar", true);
                }

            request.getSession().setAttribute("user", new UserService().updateUser(user));

            return new Router(TypeOfProceedRequest.REDIRECT, JspPath.PERSONAL_PAGE_JSP, new PersonalPageCommand());
        } catch (ServiceException e) {
            request.setAttribute("exception", e);
            return new Router(TypeOfProceedRequest.FORWARD, JspPath.ERROR_JSP, null);
        }

    }
}
