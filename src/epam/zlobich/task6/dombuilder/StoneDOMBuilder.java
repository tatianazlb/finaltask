package epam.zlobich.task6.dombuilder;

import epam.zlobich.task6.entity.Gem;
import epam.zlobich.task6.entity.Golem;
import epam.zlobich.task6.entity.MagicElement;
import epam.zlobich.task6.entity.Stone;
import epam.zlobich.task6.exception.SomeDOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by UserBD on 29-Jun-18.
 */
public class StoneDOMBuilder {
    private ArrayList<Stone> stones;
    private DocumentBuilder docBuilder;

    public StoneDOMBuilder() throws SomeDOMException {
        stones = new ArrayList<>();
        // создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {

        }
    }

    public ArrayList<Stone> getStones() {
        return stones;
    }

    public void buildSetStones(String fileName) throws SomeDOMException {
        Document doc = null;
        try {
            //request.getServletContext().getRealPath("");
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();

            NodeList gemList = root.getElementsByTagName("gem");
            for (int i = 0; i < gemList.getLength(); i++) {
                Element gemElement = (Element) gemList.item(i);
                Stone gem = buildGem(gemElement);
                stones.add(gem);
            }
            NodeList golemList = root.getElementsByTagName("golem");
            for (int i = 0; i < golemList.getLength(); i++) {
                Element golemElement = (Element) golemList.item(i);
                Stone golem = buildGolem(golemElement);
                stones.add(golem);
            }

        } catch (IOException | SAXException e) {
            throw new SomeDOMException(e);
        }
    }
    private Gem buildGem(Element gemElement) {
        Gem gem = new Gem();
        gem.setName(gemElement.getAttribute("name"));
        gem.setPrice(new Double(getElementTextContent(gemElement, "price")));
        gem.setCountry(getElementTextContent(gemElement, "country"));
        gem.setWeight(new Integer(gemElement.getAttribute("weight")));
        gem.setId(new Integer(gemElement.getAttribute("id")));

        return gem;
    }

    private Golem buildGolem(Element golemElement) {
        Golem golem = new Golem();

        golem.setName(golemElement.getAttribute("name"));
        golem.setNameOfOwner(getElementTextContent(golemElement, "owner"));
        golem.setCountry(getElementTextContent(golemElement, "country"));
        golem.setWeight(new Integer(golemElement.getAttribute("weight")));
        golem.setId(new Integer(golemElement.getAttribute("id")));

        System.out.println(golemElement.getAttribute("element"));

        golem.setMagicElement((golemElement.getAttribute("element").isEmpty())?
                MagicElement.FIRE: MagicElement.valueOf((golemElement.getAttribute("element"))));

        return golem;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}