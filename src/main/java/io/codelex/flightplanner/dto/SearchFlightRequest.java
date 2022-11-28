package io.codelex.flightplanner.dto;


import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class SearchFlightRequest {
    @NotBlank
    private final String from;
    @NotBlank
    private final String to;
    @NotBlank
    private final String departureDate;

    public SearchFlightRequest(String from, String to, String departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchFlightRequest that)) return false;
        return getFrom().equals(that.getFrom()) && getTo().equals(that.getTo()) && getDepartureDate().equals(that.getDepartureDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo(), getDepartureDate());
    }
}
