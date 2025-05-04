package com.example.hello.jakarta.cdi.event;

import com.example.hello.jakarta.util.Logged;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@Path("/email")
@Singleton
public class EmailController {

    private static final Logger log = LoggerFactory.getLogger(EmailController.class);

    @Inject
    private Event<Email> emailEvent;

    @GET
    @Path("/async")
    @Logged
    public String sendEmailAsync() {
        Email email = new Email("from@hotmail.com", "to@hotmail.com", "Subject of mail", "some async message inside the mail");
        log.info("Firing async emailEvent");
        emailEvent.fireAsync(email);

        return "Email async sent. Check logs";
    }

    @GET
    @Path("/sync")
    @Logged
    public String sendEmailSync() {
        Email email = new Email("from@hotmail.com", "to@hotmail.com", "Subject of mail", "some sync message inside the mail");
        log.info("Firing sync emailEvent");
        emailEvent.fire(email);

        return "Email sync sent. Check logs";
    }
}
