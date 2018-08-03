package epam.zlobich.task6.entity.entitybd;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UserBD {

    private String login;
    private String homeland;
    private ArrayList<Integer> idConference;

    private boolean role;

    private BufferedImage avatar;

    public UserBD()
    {
        idConference = new ArrayList<>();
        role = false;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public BufferedImage getAvatar() {
        return avatar;
    }

    public void setAvatar(BufferedImage avatar) {
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
