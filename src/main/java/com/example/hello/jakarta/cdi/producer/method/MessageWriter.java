package com.example.hello.jakarta.cdi.producer.method;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class MessageWriter implements Serializable {

    private static final Logger log = Logger.getLogger(MessageWriter.class.getName());

    private final File file;

    public MessageWriter(String path) {
        file = new File(path);
    }

    public void add(String message) throws IOException {
        try (BufferedWriter outputStream = new BufferedWriter(new FileWriter(file, true))) {
            outputStream.write(LocalDateTime.now() + " : "+ message);
            outputStream.newLine();
        }
    }

    public void clean() {
        try {
            Files.delete(Path.of(file.getPath()));
        } catch (IOException e) {
            log.warning(e.getClass() + " " + e.getMessage());
        }
    }

    public List<String> read() throws IOException {
        return Files.readAllLines(Path.of(file.getPath()));
    }
}
