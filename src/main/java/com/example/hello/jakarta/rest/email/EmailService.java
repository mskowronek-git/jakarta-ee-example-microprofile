package com.example.hello.jakarta.rest.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    public Boolean send(String from, String to, String subject, String message) {
        //Logic to send email.
        log.info(String.format("EMAIL: from %s to %s with a subject %s and message %s", from, to, subject, message));

        return true;
    }

}
