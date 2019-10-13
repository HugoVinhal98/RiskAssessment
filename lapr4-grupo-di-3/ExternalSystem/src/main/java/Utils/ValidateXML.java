/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.File;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Jmbosg
 */
public class ValidateXML {

    public static boolean validate(String xml, String xsd) throws SAXException {
        try{
            
        
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xsd));
        Validator validator = schema.newValidator();
        try {
            System.out.println("Validate XML against XSD Schema");

            validator.validate(new StreamSource(new StringReader(xml)));
            return true;
        } catch (IOException e) {
            // Handle exception while reading source
        } catch (SAXException e) {
            final List<SAXParseException> exceptions = new LinkedList<SAXParseException>();
            validator.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }
            });
            System.out.println("Error when validate XML against XSD Schema");
            System.out.println("Message: " + e.getMessage());
        }
        }catch(SAXParseException e){
            System.out.println("Complex type doesnt match with XML!");
        }
        return false;

    }
}
