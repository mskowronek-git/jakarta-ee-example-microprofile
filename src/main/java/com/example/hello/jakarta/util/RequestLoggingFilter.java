package com.example.hello.jakarta.util;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Logged
@Provider
public class RequestLoggingFilter implements ContainerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void filter(final ContainerRequestContext requestContext) {
        // example to log the invoked path
        final String invokedPath = requestContext.getUriInfo().getPath();
        log.info("Request to path {} has been made", invokedPath);
//        log.debug("some debug message");
    }
}
