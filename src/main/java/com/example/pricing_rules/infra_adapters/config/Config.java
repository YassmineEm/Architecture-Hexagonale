package com.example.pricing_rules.infra_adapters.config;

import com.example.pricing_rules.domain.ports.input.CommissionCalculator;
import com.example.pricing_rules.domain.ports.output.IPToCountryConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommissionCalculator commissionCalculator(IPToCountryConverter countryConverter) {
        return new CommissionCalculator(countryConverter);
    }
}
