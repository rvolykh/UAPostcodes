package com.pikaso.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiException extends WebApplicationException{
    private static final long serialVersionUID = 5975744143115057206L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiException.class);

    @Override
    public Response getResponse() {
        LOGGER.info("User asked for unavailable id");
        return Response.status(400)
                .entity("{\"error\":{\"code\":\"400\",\"msg\":\"Please look at APIs Reference\"}}")
                .type(MediaType.APPLICATION_JSON).
                build();
    }
    
    
    
}
