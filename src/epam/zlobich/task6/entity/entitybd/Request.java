package epam.zlobich.task6.entity.entitybd;

public class Request {
    private String title;
    private String user;
    private String themeName;

    public String getThemeName() {
        return themeName;
    }

    public String getTitle() {
        return title;
    }

    public String getUser() {
        return user;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String toTableRowWithDelete() {
        return "<tr><td>"+title+"</td><td>"+themeName+"</td><td>" +
                "<button type=\"submit\" name=\"title\" value=\""+title+"\">Delete this request</button>"+"</td><td>";
    }
}
