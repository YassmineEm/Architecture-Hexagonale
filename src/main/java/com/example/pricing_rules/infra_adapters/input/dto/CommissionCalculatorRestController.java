package com.example.pricing_rules.infra_adapters.input.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.pricing_rules.domain.ports.input.CommissionCalculator;
import com.example.pricing_rules.domain.ports.Collaboration; 

@RestController
@RequestMapping(value = "/commission", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommissionCalculatorRestController {

    private final CommissionCalculator commissionCalculator;

    @Autowired
    public CommissionCalculatorRestController(CommissionCalculator commissionCalculator) {
        this.commissionCalculator = commissionCalculator;
    }

    @PostMapping
    public ResponseEntity<Double> calculateCommission(@RequestBody Collaboration collaboration) {
        if (collaboration == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Double commissionValue = commissionCalculator.calculateCommission(collaboration);
            return ResponseEntity.ok(commissionValue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}