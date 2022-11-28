package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.services.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api/flights")
public class AdminController {
    final CommonService service;

    public AdminController(CommonService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Flight getFlight(@PathVariable int id) {
        return service.getFlight(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Flight addFlight(@Valid @RequestBody AddFlightRequest flight) {
        if (flight.getDepartureTime().compareTo(flight.getArrivalTime()) >= 0 || (flight.getFrom().equals(flight.getTo()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return service.addFlight(flight);
    }

    @DeleteMapping("{id}")
    public synchronized void deleteFlight(@PathVariable int id) {
        service.deleteFlight(id);
    }
}
