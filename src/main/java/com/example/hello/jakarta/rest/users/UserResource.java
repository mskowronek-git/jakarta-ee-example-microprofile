package com.example.hello.jakarta.rest.users;

import com.example.hello.jakarta.util.Logged;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Optional;

@Path("/users")
@Singleton
public class UserResource {

    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Inject
    private UserInMemDatasource userInMemDatasource;

    /**
     * Parameters can be sent to Jakarta RESTful like this
     * URI path (or path parameter): Using @PathParam annotation.
     * Query: Using @QueryParam annotation.
     * Header: Using @HeaderParam annotation.
     * Form: Using @FormParam annotation.
     * Cookie: Using @CookieParam annotation.
     * Matrix: Using @MatrixParam annotation.
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Logged
    public Response list() throws JsonProcessingException {
        log.debug("a debug message");
        log.info("an info message");
        return Response.ok(userInMemDatasource.listAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") String id) {
        Optional<User> user = userInMemDatasource.findById(id);
        if (!user.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(user.get()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /* The query parameter has a way to define a default value in case the query parameter is not informed
    @DefaultValue(“rhuan@test.com”)
     */
    public Response list(@QueryParam("email") String email) {
        if (email != null) {
            return Response
                    .ok(userInMemDatasource.listByEmail(email))
                    .build();
        }

        return Response.ok(userInMemDatasource.listAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(User user) {
        user = userInMemDatasource.persist(user);
        return Response
                .created(URI.create(String.format("/users/%s", user.getId())))
                .build();
    }
}
