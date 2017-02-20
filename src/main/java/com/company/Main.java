package com.company;

import com.company.exceptions.ValidationException;
import org.apache.log4j.Logger;

import javax.xml.transform.TransformerException;

public class Main {
public static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args){
        if (args.length >= 5){
            boolean xmlCorrect = false;
            try{
                logger.info("Start validation process of " + args[0] + " with " + args[1]);
                xmlCorrect = Validation.validate(args[0], args[1]);
                logger.info("Validation is finished. Results: file is correct");
            }catch (ValidationException e){
                logger.error(e.getMessage());
            }

            if (xmlCorrect){
                try{
                    logger.info("Start transformation process of " + args[0] + " with " + args[2]);
                    Transformation.transform(args[0], args[2], args[3]);
                    logger.info("Transformation is finished. Results: file was transformed correctly to " + args[3]);

                    logger.info("Start validation process of " + args[3] + " with " + args[4]);
                    Validation.validate(args[3], args[4]);
                    logger.info("Validation is finished. Results: file is correct");
                }catch (TransformerException e){
                    logger.error(e.getMessage());
                }catch (ValidationException e){
                    logger.error(e.getMessage());
                }
            }
        }else{
            logger.error("Wrong count of arguments");
        }
    }
}
