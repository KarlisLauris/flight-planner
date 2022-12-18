package io.codelex.flightplanner.repositories;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightRequest;

import java.util.Set;

public interface RepositoryInterface {
    void clear();

    void deleteFlight(int id);

    Flight addFlight(AddFlightRequest flight);

    Flight getFlight(int id);

    PageResult<Flight> searchFlight(SearchFlightRequest request);

    Set<Airport> searchAirport(String search);

}
