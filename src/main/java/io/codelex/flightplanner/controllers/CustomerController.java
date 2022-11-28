package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightRequest;
import io.codelex.flightplanner.services.CommonService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CustomerController {
    final CommonService service;

    public CustomerController(CommonService service) {
        this.service = service;
    }

    @GetMapping("/flights/{id}")
    public Flight getFlight(@PathVariable int id) {
        return service.getFlight(id);
    }

    @PostMapping("/flights/search")
    public synchronized PageResult<Flight> searchFlight(@RequestBody @Valid SearchFlightRequest request) {
        return service.searchFlight(request);
    }

    @GetMapping("/airports")
    public Set<Airport> searchFlight(@RequestParam String search) {
        return service.searchAirport(search);
    }
}
