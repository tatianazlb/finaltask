package epam.zlobich.task6.entity.entitybd;

import java.util.Date;

public class Theme {
    private String name;
    private int idConference;
    private Date date;

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getIdConference() {
        return idConference;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIdConference(int idConference) {
        this.idConference = idConference;
    }
}
