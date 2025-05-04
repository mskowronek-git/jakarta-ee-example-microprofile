package com.example.hello.jakarta.cdi.decorator;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Path("/profit")
@Singleton
public class ProfitController {

    @Inject
    private ProfitCalculator profitCalculator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response appendMessage(@QueryParam("income") String incomeString,
                                  @QueryParam("spent") String spentString) {

        BigDecimal income = BigDecimal.valueOf(Double.parseDouble(incomeString)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal spent = BigDecimal.valueOf(Double.parseDouble(spentString)).setScale(2, RoundingMode.HALF_UP);

        BigDecimal profit = profitCalculator.calculate(income, spent);

        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("profit", profit);
        return Response.ok(successResponse)
                .build();
    }


}
