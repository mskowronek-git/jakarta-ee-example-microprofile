package com.example.hello.jakarta;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 *
 */
@ApplicationPath("/api")
public class HelloJakartaRestApplication extends Application {

    //You can use the following code to only expose a limited amount of rest controllers
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> classes = new HashSet<>();
//        classes.add(UserResource.class);
//        return classes;
//    }


}
