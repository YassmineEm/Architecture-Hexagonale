package com.example.pricing_rules.domain.ports.output;
import com.example.pricing_rules.domain.ports.Country;

public interface IPToCountryConverter {
    Country getCountryFromIp(String ip);
}