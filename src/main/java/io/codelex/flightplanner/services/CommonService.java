package io.codelex.flightplanner.services;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightRequest;
import io.codelex.flightplanner.repositories.MemoryRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CommonService {
    final MemoryRepository repository;

    public CommonService(MemoryRepository repository) {
        this.repository = repository;
    }

    public synchronized void deleteFlight(int id) {
        repository.deleteFlight(id);
    }

    public synchronized Flight addFlight(AddFlightRequest flight) {
        return repository.addFlight(flight);
    }

    public Flight getFlight(int id) {
        return repository.getFlight(id);
    }

    public void clear() {
        repository.clear();
    }

    public PageResult<Flight> searchFlight(SearchFlightRequest request) {
        return repository.searchFlight(request);
    }

    public Set<Airport> searchAirport(String search) {
        return repository.searchAirport(search);
    }
}
