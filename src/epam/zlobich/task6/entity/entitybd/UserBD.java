package epam.zlobich.task6.entity.entitybd;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBd {

    private String login;
    private String homeland;
    private ArrayList<Integer> idConference;

    private boolean role;

    private BufferedImage avatar;

    public UserBd()
    {
        idConference = new ArrayList<>();
        role = false;


      /* */
    }

    public void setAvatarByBlob(Blob blob)
    {
        try {
            byte[] bytes = blob.getBytes(1l, (int) blob.length());
            InputStream in = new ByteArrayInputStream(bytes);
            avatar = ImageIO.read(in);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            avatar = null;
            try {
                avatar = ImageIO.read(new File("C:\\Users\\User\\IdeaProjects\\EPAM_6_Try\\web\\2_3.jpg"));
            } catch (IOException ee) {
                ee.printStackTrace();
            }
        }
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public BufferedImage getAvatar() {
        return avatar;
    }

    public void setAvatar(BufferedImage avatar) {
        if(avatar!=null)
        this.avatar = avatar;
    }

    public boolean isRole() {
        return role;
    }

    public void setHomeland(String homeland) {
        this.homeland = homeland;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getLogin() {
        return login;
    }

    public ArrayList<Integer> getIdConference() {
        return idConference;
    }

    public String getHomeland() {
        return homeland;
    }
}
