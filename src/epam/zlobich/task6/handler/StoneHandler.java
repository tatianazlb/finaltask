package epam.zlobich.task6.handler;

import epam.zlobich.task6.entity.Gem;
import epam.zlobich.task6.entity.Golem;
import epam.zlobich.task6.entity.MagicElement;
import epam.zlobich.task6.entity.Stone;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by UserBD on 25-Jun-18.
 */
public class StoneHandler extends DefaultHandler {

    private ArrayList<Stone> stones;
    Stone currentStone;

    Boolean bCountry=false, bPrice=false, bOwner=false;

    @Override
    public void startDocument() {

        stones = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {

       switch (qName){
            case "gem":{
                currentStone = new Gem(attrs.getValue(0),new Integer(attrs.getValue(1)));
                break;
            }
            case "golem":
            {
                MagicElement magicElement = (attrs.getValue(3) == null)? MagicElement.FIRE : MagicElement.valueOf(attrs.getValue(3));
                currentStone = new Golem(attrs.getValue(0),new Integer(attrs.getValue(1)), magicElement);
                break;
            }
            case "price":
            {
                bPrice = true;
                break;
            }
           case "country":
           {
               bCountry = true;
               break;
           }
           case "owner":
           {
               bOwner = true;
               break;
           }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        if (bOwner)
        {
            ((Golem)currentStone).setNameOfOwner(new String(ch, start, length));
            bOwner = false;
        }
        else if (bCountry)
        {
            currentStone.setCountry(new String(ch, start, length));
            bCountry = false;
        }
        else if (bPrice)
        {
            ((Gem)currentStone).setPrice(new Double(new String(ch, start, length)));
            bPrice = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equals("gem")||qName.equals("golem"))
        {
            stones.add(currentStone);
        }
    }

    public ArrayList<Stone> getStones() {
        return stones;
    }

    @Override
    public void endDocument() {

    }
}

