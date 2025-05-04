package com.example.hello.jakarta.cdi.producer.field;

import com.example.hello.jakarta.cdi.producer.method.MessageWriter;
import jakarta.enterprise.inject.Produces;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class MessageWriterProducerField {

    private static final Logger log = Logger.getLogger(MessageWriterProducerField.class.getName());

    private static final String HOME_PATH = System.getProperty("user.home") + "/tmp/";

    private static final String NAME_PATTERN = "MESSAGE_%d.txt";

    @Produces
    @MessageField
    MessageWriter messageWriter;

    public MessageWriterProducerField() {
        String fileName = String.format(NAME_PATTERN, Timestamp.valueOf(LocalDateTime.now()).getTime());
        log.info(() -> String.format("Creating file message: %s", HOME_PATH + fileName));

        this.messageWriter = new MessageWriter(HOME_PATH + fileName);
    }

}
