package com.example.pricing_rules.domain.ports;

import java.util.Objects;

public class Country {
    private final String name;
    private final String code;

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() { return name; }
    public String getCode() { return code; }

    @Override
    public int hashCode() { return Objects.hash(name, code); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Country country = (Country) obj;
        return Objects.equals(name, country.name) &&
               Objects.equals(code, country.code);
    }
}
