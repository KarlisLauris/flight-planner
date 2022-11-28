package io.codelex.flightplanner.repositories;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository

public class MemoryRepository implements RepositoryInterface {

    private final List<Flight> flights = new ArrayList<>();

    public synchronized void deleteFlight(int id) {
        flights.removeIf(flight -> flight.getId() == id);
    }

    public synchronized Flight addFlight(AddFlightRequest flight) {
        Flight createdFlight = new Flight(flights.size(), flight.getFrom(), flight.getTo(), flight.getCarrier(), flight.getDepartureTime(), flight.getArrivalTime());

        if (flights.contains(createdFlight)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        flights.add(createdFlight);
        return createdFlight;
    }

    public Flight getFlight(int id) {
        return flights.stream()
                .filter(flight -> flight.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void clear() {
        flights.clear();
    }

    public PageResult<Flight> searchFlight(SearchFlightRequest request) {
        if (request.getTo().equals(request.getFrom())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Flight> newList = flights.stream()
                .filter(flight -> flight.getFrom().getAirport().equals(request.getFrom())
                        && flight.getTo().getAirport().equals(request.getTo())
                        && flight.getDepartureTime().toLocalDate().equals(LocalDate.parse(request.getDepartureDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .toList();
        return new PageResult<>(0, newList.size(), newList);
    }

    public Set<Airport> searchAirport(String search) {
        String formatSearch = search.toLowerCase().trim();
        Set<Airport> result = flights.stream()
                .map(Flight::getFrom)
                .filter(airport -> (
                        airport.getAirport().toLowerCase().contains(formatSearch)
                                || airport.getCity().toLowerCase().contains(formatSearch)
                                || airport.getCountry().toLowerCase().contains(formatSearch))
                ).collect(Collectors.toSet());
        result.addAll(flights.stream()
                .map(Flight::getTo)
                .filter(airport -> (
                        airport.getAirport().toLowerCase().contains(formatSearch)
                                || airport.getCity().toLowerCase().contains(formatSearch)
                                || airport.getCountry().toLowerCase().contains(formatSearch))
                ).collect(Collectors.toSet()));
        return result;
    }


}
