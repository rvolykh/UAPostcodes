package com.pikaso.rest.context;



import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.pikaso.rest.controller.ApiController;


@ApplicationPath("/api")
public class ApplicationContext extends Application {
 
    @Override
    public Set<Class<?>> getClasses() {
        // check database
        InitDB.okTables();
        // map classes
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(ApiController.class);
        return classes;
    }
    

}
