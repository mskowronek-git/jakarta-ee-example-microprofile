package com.example.hello.jakarta.rest.users;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserInMemDatasource {

    private Map<String, User> users;

    @PostConstruct
    public void init() {
        users = new HashMap<>();
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    public User persist(User user) {
        if (user.getId() == null) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
        }
        users.put(user.getId(), user);
        return user;
    }

    public List<User> listAll() {
        return users
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    public List<User> listByEmail(String email) {
        return users
                .values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .collect(Collectors.toList());
    }
}
