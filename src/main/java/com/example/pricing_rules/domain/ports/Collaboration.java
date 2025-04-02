package com.example.pricing_rules.domain.ports;

public class Collaboration {
    private String freelanceIp;
    private String customerIp;
    private Integer commercialRelation;

    public Collaboration() {
    }

    public Collaboration(String freelanceIp, String customerIp, Integer commercialRelation) {
        this.freelanceIp = freelanceIp;
        this.customerIp = customerIp;
        this.commercialRelation = commercialRelation;
    }

    
    public String getFreelanceIp() {
        return freelanceIp;
    }

    public void setFreelanceIp(String freelanceIp) {
        this.freelanceIp = freelanceIp;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public Integer getCommercialRelation() {
        return commercialRelation;
    }

    public void setCommercialRelation(Integer commercialRelation) {
        this.commercialRelation = commercialRelation;
    }
}