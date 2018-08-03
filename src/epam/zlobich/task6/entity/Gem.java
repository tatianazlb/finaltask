package epam.zlobich.task6.entity;

/**
 * Created by UserBD on 24-Jun-18.
 */
public class Gem extends Stone{
    private double price;

    public Gem()
    {

    }

    public Gem(String name, int weight )
    {
        this.name = name;
        this.weight = weight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toTableString() {
        return "<tr><td>"+name+"</td><td>"+weight+"</td><td>"+country+"</td><td>"+price+"</td></tr>";
    }
}
