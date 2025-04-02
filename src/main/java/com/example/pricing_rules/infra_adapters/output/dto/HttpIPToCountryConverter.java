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
import org.springframework.http.MediaType;

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
        try {
           String apiUrl = String.format("%s/%s?access_key=%s", url, ip, accessKey);
           ResponseEntity<GeoIp> response = restTemplate.exchange(
              apiUrl, 
              HttpMethod.GET, 
              getHttpEntity(), 
              GeoIp.class
            );
        
            GeoIp geoIp = response.getBody();
            if (geoIp == null || geoIp.getCountryCode() == null) {
               throw new RuntimeException("Invalid response from IPStack API");
            }
            return new Country(geoIp.getCountryName(), geoIp.getCountryCode());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get country from IP: " + ip, e);
        }
    }

    private HttpEntity<String> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}

