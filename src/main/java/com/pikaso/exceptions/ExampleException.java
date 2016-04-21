package com.pikaso.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleException extends RuntimeException{
    private static final long serialVersionUID = -24112571464258758L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleException.class);
    
    public ExampleException(String message){
        LOGGER.error(message);
    }
    
    public ExampleException(String message, Exception e){
        LOGGER.error(message,e);
    }

}
