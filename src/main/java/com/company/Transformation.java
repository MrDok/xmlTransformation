package com.company;

import org.apache.log4j.Logger;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * @author by user on 20.02.2017.
 */
public class Transformation{
    public static final Logger logger = Logger.getLogger(Transformation.class);

    /**
     * transformation xml file via xslt file
     * @param xml xml file name
     * @param xslt xslt file name
     * @param output output file
     * @return
     */
    public static boolean transform(String xml, String xslt, String output) throws TransformerException{
        TransformerFactory factory = TransformerFactory.newInstance();

        Transformer transformer;
        try{
            transformer = factory.newTransformer(new StreamSource(new File(xslt)));
        }catch (TransformerConfigurationException e){
            logger.error("couldn't read xslt file correctly");
            throw new TransformerException("couldn't read xslt file correctly");
        }

        if (transformer != null){
            Source xmlFile = new StreamSource(new File(xml));
            try{
                transformer.transform(xmlFile, new StreamResult(new File(output)));

                return true;
            }catch (TransformerException e){
                logger.error("Error in xml file transformation");
                throw new TransformerException("Error in xml file transformation");
            }
        }

        return false;
    }
}
