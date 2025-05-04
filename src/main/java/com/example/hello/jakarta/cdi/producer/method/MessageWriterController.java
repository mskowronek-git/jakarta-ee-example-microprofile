package com.example.hello.jakarta.cdi.producer.method;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.Objects;

@Path("/messages/method")
@Singleton
public class MessageWriterController {

    @Inject
    @Message
    private MessageWriter messageWriter;

    @GET
    @Path("/append")
    @Produces(MediaType.APPLICATION_JSON)
    public Response appendMessage(@QueryParam("message") String message) throws IOException {

        if (Objects.nonNull(message) && !message.isEmpty()) {
            messageWriter.add(message);
        }

        return Response.ok(messageWriter.read())
                .build();
    }

    @GET
    @Path("/clean")
    @Produces(MediaType.APPLICATION_JSON)
    public Response clean() {
        messageWriter.clean();

        return Response.ok()
                .build();
    }


}
