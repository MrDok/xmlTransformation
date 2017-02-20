package com.company;

import com.company.exceptions.ValidationException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * @author by user on 20.02.2017.
 */
public class Validation{
    public final static Logger logger = Logger.getLogger(Validation.class);

    /**
     * validate xml file with xsd file
     * @param xml
     * @param xsd
     * @return
     */
    public static boolean validate(String xml, String xsd) throws ValidationException{
        Source xmlFile = new StreamSource(new File(xml));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Schema schema = null;
        try{
            schema = schemaFactory.newSchema(new File(xsd));
        }catch (SAXException e){
            logger.error("Problems with reading xsd file");
            throw new ValidationException("Problems with reading xsd file");
        }catch (Exception e){
            logger.error("Inner system error");
        }
        if (schema != null){
            Validator validator = schema.newValidator();
            try{
                validator.validate(xmlFile);

                return true;
            }catch (SAXException e){
                logger.error("file is not valid");
                throw new ValidationException("file is not valid");
            }catch (IOException e){
                logger.error("problems with reading file");
                throw new ValidationException("problems with reading xml file");
            }catch (Exception e){
                logger.error("Inner system error");
            }
        }

        return false;
    }
}
