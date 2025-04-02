package com.example.pricing_rules.domain.ports.input;

import com.example.pricing_rules.domain.ports.Country;
import com.example.pricing_rules.domain.ports.Collaboration;
import com.example.pricing_rules.domain.ports.Commission;
import com.example.pricing_rules.domain.ports.output.IPToCountryConverter;

public class CommissionCalculator {

    private IPToCountryConverter ipToCountryConverter;

    public CommissionCalculator(IPToCountryConverter ipToCountryConverter) {
        this.ipToCountryConverter = ipToCountryConverter;
    }

    public Double calculateCommission(Collaboration collaboration) {
        Country freelanceCountry = ipToCountryConverter.getCountryFromIp(collaboration.getFreelanceIp());
        Country customerCountry = ipToCountryConverter.getCountryFromIp(collaboration.getCustomerIp());

        Commission commission = new Commission(freelanceCountry, customerCountry, collaboration.getCommercialRelation());
        return commission.calculate();
    }
}

