package com.example.hello.jakarta.cdi.event;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.ObservesAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailObserver {

    private static final Logger log = LoggerFactory.getLogger(EmailObserver.class);

    public void send(@Observes Email email) {
        log.info("Sync send email received");
        this.sendEmail(email);
    }

    public void sendAsync(@ObservesAsync Email email) {
        log.info("Async send email received");
        this.sendEmail(email);
    }

    private void sendEmail(Email email) {
        // Logic to send email.
        log.info("send email received = {}", email);
    }
}
