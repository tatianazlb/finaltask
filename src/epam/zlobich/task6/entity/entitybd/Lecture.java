package epam.zlobich.task6.entity.entitybd;

public class Lecture {
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
}
