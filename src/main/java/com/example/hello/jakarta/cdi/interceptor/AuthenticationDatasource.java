package com.example.hello.jakarta.cdi.interceptor;

import jakarta.annotation.PostConstruct;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class AuthenticationDatasource implements Serializable {

    private Map<String, String> userDatasource;

    @PostConstruct
    public void init() {
        userDatasource = new ConcurrentHashMap<>();
        userDatasource.put("inaki", "inaki");
        userDatasource.put("admin", "admin");
    }

    public boolean validate(String username, String password) {

        return Objects.equals(userDatasource.get(username), password);
    }
}
