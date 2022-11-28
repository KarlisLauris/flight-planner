package io.codelex.flightplanner.dto;

import io.codelex.flightplanner.domain.Airport;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AddFlightRequest {
    final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @NotNull
    @Valid
    private final Airport from;
    @NotNull
    @Valid
    private final Airport to;
    @NotBlank
    private final String carrier;
    @NotNull
    private final LocalDateTime departureTime;
    @NotNull
    private final LocalDateTime arrivalTime;

    public AddFlightRequest(Airport from, Airport to, String carrier, String departureTime, String arrivalTime) {
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = LocalDateTime.parse(departureTime, timeFormatter);
        this.arrivalTime = LocalDateTime.parse(arrivalTime, timeFormatter);
    }

    public Airport getFrom() {
        return from;
    }

    public Airport getTo() {
        return to;
    }

    public String getCarrier() {
        return carrier;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddFlightRequest that)) return false;
        return getFrom().equals(that.getFrom()) && getTo().equals(that.getTo()) && getCarrier().equals(that.getCarrier()) && getDepartureTime().equals(that.getDepartureTime()) && getArrivalTime().equals(that.getArrivalTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo(), getCarrier(), getDepartureTime(), getArrivalTime(), timeFormatter);
    }
}
