package com.example.hello.jakarta.cdi.interceptor;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/authenticate")
@Singleton
public class AuthenticatorController {

    @Inject
    private AuthenticationDatasource authenticationDatasource;

    @Inject
    private SecuredService securedService;

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response appendMessage(@QueryParam("username") String username,
                                  @QueryParam("password") String password) {

        if (!authenticationDatasource.validate(username, password)) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "forbidden");

            return Response.status(Response.Status.FORBIDDEN)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
        }

        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("message", securedService.generateText(username));
        return Response.ok(successResponse)
                .build();
    }


}
