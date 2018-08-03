package epam.zlobich.task6.entity;

/**
 * Created by UserBD on 24-Jun-18.
 */
abstract public class Stone {
    protected int weight;
    protected String name;
    protected String country;
    protected int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    abstract public String toTableString();
}
