package com.example.pricing_rules.domain.ports;

public class Commission {
    private static final Double DEFAULT_COMMISSION = 10d;

    private Country freelanceOrigin;
    private Country customerOrigin;
    private Integer commercialRelation;

    public Commission(Country freelanceOrigin, Country customerOrigin, Integer commercialRelation) {
        this.freelanceOrigin = freelanceOrigin;
        this.customerOrigin = customerOrigin;
        this.commercialRelation = commercialRelation;
    }

    public Double calculate() {
        if (freelanceOrigin.getCode().equals(customerOrigin.getCode())) {
            return 2d;
        }
        if (customerOrigin.getCode().equals("GB") && commercialRelation >= 3) {
            return 5d;
        }
        return DEFAULT_COMMISSION;
    }
}

