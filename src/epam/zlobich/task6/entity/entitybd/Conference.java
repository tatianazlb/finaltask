package epam.zlobich.task6.entity.entitybd;

public class Conference {
    private int id;
    private String name;
    private String place;

    public Conference()
    {

    }

    public Conference(int i, String n, String p)
    {
        id = i;
        name=n;
        place=p;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

}
