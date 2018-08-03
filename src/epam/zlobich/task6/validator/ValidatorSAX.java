package epam.zlobich.task6.validator;

import epam.zlobich.task6.exception.ValidationException;
import epam.zlobich.task6.handler.StoneHandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class ValidatorSAX {
    private StoneHandler handler = new StoneHandler();

    public StoneHandler getHandler() {
        return handler;
    }

    public void validate(String filename, String schemaname) throws ValidationException {
        Schema schema = null;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {

            schema = factory.newSchema(new File(schemaname));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);

            SAXParser parser = spf.newSAXParser();

            parser.parse(filename, handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ValidationException(e);
        }
    }
}
