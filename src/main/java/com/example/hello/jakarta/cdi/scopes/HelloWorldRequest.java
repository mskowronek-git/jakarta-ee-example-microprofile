package com.example.hello.jakarta.cdi.scopes;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;

import java.time.LocalDateTime;

@RequestScoped
public class HelloWorldRequest {

    private String message;

    private LocalDateTime localDateTime;

    @PostConstruct
    public void init() {
        this.message = "Hello world";
        this.localDateTime = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
