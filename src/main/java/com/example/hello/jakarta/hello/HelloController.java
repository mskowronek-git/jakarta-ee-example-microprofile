package com.example.hello.jakarta.hello;

import com.example.hello.jakarta.util.Logged;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GET
    @Logged
    public String sayHello() {
        log.debug("hello debug");
        log.info("hello info");

        return "Hello awesome World";
    }

    @GET
    @Path("/foo")
    public String sayFoo() {
        log.debug("hello debug");
        log.info("hello info");

        int i = 0;

        return "Hello mvmvmvmv World";
    }


    @GET
    @Path("/bar")
    public String sayBar() {

        return "Hello bar World";
    }
}
