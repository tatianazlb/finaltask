package epam.zlobich.task6.staxparser;

import epam.zlobich.task6.entity.Gem;
import epam.zlobich.task6.entity.Golem;
import epam.zlobich.task6.entity.MagicElement;
import epam.zlobich.task6.entity.Stone;
import epam.zlobich.task6.exception.SomeStAXException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ParserStAX {

    private ArrayList<Stone> stones = new ArrayList<>();
    private XMLInputFactory inputFactory;


    public ParserStAX() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public ArrayList<Stone> getStones() {
        return stones;
    }

    public void buildSetStones(String fileName) throws SomeStAXException {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals("gem")) {
                        Stone st = buildGem(reader);
                        stones.add(st);
                    }
                    else if (name.equals("golem"))
                    {
                        Stone st = buildGolem(reader);
                        stones.add(st);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException ex) {
            throw new SomeStAXException(ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new SomeStAXException(e);
            }

        }
    }

        private Gem buildGem (XMLStreamReader reader) throws XMLStreamException {
            Gem stone = new Gem();

            stone.setId(new Integer(reader.getAttributeValue(null, "id")));
            stone.setName(reader.getAttributeValue(null, "name"));
            stone.setWeight(new Integer(reader.getAttributeValue(null, "weight")));

            String name;
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:{
                        name = reader.getLocalName();
                        switch (name) {
                            case "country":{
                                stone.setCountry(getXMLText(reader));
                                break;}
                            case "price":{
                                stone.setPrice(Double.parseDouble(getXMLText(reader)));
                                break;}
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT:{
                        name = reader.getLocalName();
                        if (name.equals("gem")) {
                            return stone;
                        }
                        break;
                    }
                }
            }
            throw new XMLStreamException("Unknown element in tag gem");

        }

        private Golem buildGolem (XMLStreamReader reader) throws XMLStreamException {
            Golem stone = new Golem();

            stone.setId(new Integer(reader.getAttributeValue(null, "id")));
            stone.setName(reader.getAttributeValue(null, "name"));
            stone.setWeight(new Integer(reader.getAttributeValue(null, "weight")));

            stone.setMagicElement(reader.getAttributeValue(null, "element")==null? MagicElement.FIRE : MagicElement.valueOf(reader.getAttributeValue(null, "element")));

            String name;
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:{
                        name = reader.getLocalName();
                        switch (name) {
                            case "country":{
                                stone.setCountry(getXMLText(reader));
                                break;}
                            case "owner":{
                                stone.setNameOfOwner(getXMLText(reader));
                                break;}
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT:{
                        name = reader.getLocalName();
                        if (name.equals("golem")) {
                            return stone;
                        }
                        break;
                    }
                }
            }
            throw new XMLStreamException("Unknown element in tag golem");
        }


        private String getXMLText (XMLStreamReader reader) throws XMLStreamException {
            String text = null;
            if (reader.hasNext()) {
                reader.next();
                text = reader.getText();
            }
            return text;
        }
}

