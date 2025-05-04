package com.example.hello.jakarta.rest.users;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

public class UserService {

    private static final String TARGET_SERVICE = "http://localhost:8090/api/users";

    public User findById(String id) {
        Client client = ClientBuilder.newClient();
        User user = client
                .target(TARGET_SERVICE + "/" + id).request(MediaType.APPLICATION_JSON)
                .get(User.class);

        client.close();

        return user;
    }
}
