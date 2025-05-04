package com.example.hello.jakarta.cdi.decorator;

import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Decorator
@Priority(200)
public class TributeDecorator implements Calculator {

    @Inject
    @Delegate
    private Calculator calculator;

    @Override
    public BigDecimal calculate(BigDecimal income, BigDecimal spent) {
        BigDecimal newIncome = income.multiply(BigDecimal.valueOf(0.9).setScale(2, RoundingMode.HALF_UP));

        return calculator.calculate(newIncome, spent);
    }
}
