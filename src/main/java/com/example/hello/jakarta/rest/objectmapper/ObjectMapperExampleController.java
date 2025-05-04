package com.example.hello.jakarta.rest.objectmapper;

import com.example.hello.jakarta.util.Logged;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Map;

@Path("/objectmapper")
@Singleton
public class ObjectMapperExampleController {

    private static final Logger log = LoggerFactory.getLogger(ObjectMapperExampleController.class);

    @Inject
    private ObjectMapper objectMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Logged
    public Response list() throws JsonProcessingException {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String json = "{\"name\":\"inaki\", \"age\":\"37\"}";
        Map<String, Object> map = objectMapper.readValue(json, Map.class);
//        LocalDateTime now = LocalDateTime.now();
//        map.put("localdateUtc", now);
//        map.put("localdateTokyo", now.atZone(ZoneId.of("Asia/Tokyo")));
//        map.put("localdateZero", now.atOffset(ZoneOffset.ofHours(0)));
//        map.put("localdatePlusOne", now.atOffset(ZoneOffset.ofHours(1)));
        map.put("someInstant", Instant.now());

        log.info(objectMapper.writeValueAsString(map));

        return Response.ok(map).build();
    }
}
