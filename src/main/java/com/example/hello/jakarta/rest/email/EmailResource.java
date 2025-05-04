package com.example.hello.jakarta.rest.email;

import com.example.hello.jakarta.rest.users.User;
import com.example.hello.jakarta.rest.users.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/emails")
public class EmailResource {

    @Inject
    private UserService userService;

    @Inject
    private EmailService emailService;

    @POST
    @Path("/{id}")
    public Response send(@PathParam("id") String userId,
                         @DefaultValue("Hello") @FormParam("subject") String subject,
                         @DefaultValue("Hello! Welcome!") @FormParam("message") String message) {

        User user = userService.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        emailService.send("system@test.test", user.getEmail(), subject, message);
        return Response.ok().build();
    }

}
