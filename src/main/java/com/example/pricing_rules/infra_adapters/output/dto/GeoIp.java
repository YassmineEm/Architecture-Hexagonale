package com.example.pricing_rules.infra_adapters.output.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoIp {
    @JsonProperty("country_name")
    private String countryName;
    
    @JsonProperty("country_code")
    private String countryCode;

    
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}