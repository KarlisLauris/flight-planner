package io.codelex.flightplanner.domain;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Airport {
    @NotBlank
    private final String country;
    @NotBlank
    private final String city;
    @NotBlank
    private final String airport;

    public Airport(String country, String city, String airport) {
        this.country = country.trim().substring(0, 1).toUpperCase() + country.trim().toLowerCase().substring(1);
        this.city = city.trim().substring(0, 1).toUpperCase() + city.trim().toLowerCase().substring(1);
        this.airport = airport.trim().toUpperCase();
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAirport() {
        return airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport airport1)) return false;
        return getCountry().equalsIgnoreCase(airport1.getCountry()) && getCity().equalsIgnoreCase(airport1.getCity()) && getAirport().equalsIgnoreCase(airport1.getAirport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getCity(), getAirport());
    }
}
