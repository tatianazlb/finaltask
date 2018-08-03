package epam.zlobich.task6.entity;

/**
 * Created by UserBD on 24-Jun-18.
 */
public class Golem extends Stone{

    private String nameOfOwner;
    private MagicElement magicElement;

    public Golem(){}

    public Golem(String name, int weight, MagicElement magicElement)
    {
        this.magicElement = magicElement;
        this.name = name;
        this.weight = weight;
    }

    public void setMagicElement(MagicElement magicElement) {
        this.magicElement = magicElement;
    }

    public void setNameOfOwner(String nameOfOwner) {
        this.nameOfOwner = nameOfOwner;
    }

    @Override
    public String toTableString() {
        return "<tr><td>"+name+"</td><td>"+weight+"</td><td>"+country+"</td><td>"+magicElement+"</td><td>"+nameOfOwner+"</td></tr>";
    }
}
