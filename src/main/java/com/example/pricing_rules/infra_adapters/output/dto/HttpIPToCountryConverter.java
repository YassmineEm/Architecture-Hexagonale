package com.example.pricing_rules.infra_adapters.output.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.pricing_rules.domain.ports.output.IPToCountryConverter;
import com.example.pricing_rules.domain.ports.Country;

@Service
public class HttpIPToCountryConverter implements IPToCountryConverter {

    private RestTemplate restTemplate;
    private final String url;
    private final String accessKey;

    @Autowired
    public HttpIPToCountryConverter(RestTemplate restTemplate,
                                    @Value("${pricing-rules.geoip.url}") String url,
                                    @Value("${pricing-rules.geoip.access_key}") String accessKey) {
        this.restTemplate = restTemplate;
        this.url = url;
        this.accessKey = accessKey;
    }

    @Override
    public Country getCountryFromIp(String ip) {
        ResponseEntity<GeoIp> response = restTemplate.getForEntity(String.format("%s/%s", url, ip), GeoIp.class);
        GeoIp geoIp = response.getBody();
        return new Country(geoIp.getCountryName(), geoIp.getCountryCode());
    }

    private HttpEntity<String> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("access_key", accessKey);
        return new HttpEntity<>(headers);
    }
}

