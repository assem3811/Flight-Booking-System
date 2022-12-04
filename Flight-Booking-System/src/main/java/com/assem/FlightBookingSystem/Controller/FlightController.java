package com.assem.FlightBookingSystem.Controller;

import com.assem.FlightBookingSystem.Model.Customer;
import com.assem.FlightBookingSystem.Model.Flight;
import com.assem.FlightBookingSystem.Model.Ticket;
import com.assem.FlightBookingSystem.Repository.CustomerRepository;
import com.assem.FlightBookingSystem.Repository.FlightRepository;
import com.assem.FlightBookingSystem.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/flights")
public class FlightController {

    @Autowired
    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;

    public FlightController(FlightRepository flightRepository, TicketRepository ticketRepository) {
        this.flightRepository = flightRepository;
        this.ticketRepository = ticketRepository;
    }


    @GetMapping("/all")
    ResponseEntity<List<Flight>> getAllFlights() {
        return new ResponseEntity<>((List<Flight>) flightRepository.findAll(), HttpStatus.OK);
    }

    //Find a Flight by Id

    @GetMapping("/admin/{flightId}")
    Flight getFlightById(
            @PathVariable("flightId") Long id
    ) {
        Flight flight = flightRepository.findById(id).get();
        return flight;
    }

    //Search by source and destination...
    @GetMapping("{destination}/{source}")
    List<Flight> getFlightBySrcAndDest(
            @PathVariable("destination") String dest,
            @PathVariable("source") String src) {

        return flightRepository.findFlightsBySrcAndDest(dest, src);
    }


    //****** REQUEST BODY FORMAT*****//
//    {
//        "source": "China",
//            "destination": "USA",
//            "departureTime": "2022-08-25T10:00:00",
//            "arrivalTime": "2022-08-25T10:00:00",
//    }

    @PostMapping("/admin")
    Flight addFlight(
            @RequestBody Flight flight) {
        return flightRepository.save(flight);
    }

    //

    @PutMapping("/admin/{flightId}")
    Flight updateFlightDetails(
            @PathVariable("flightId") Long id,
            @RequestBody Flight flight
    ) {
        Flight flight1 = flightRepository.findById(id).get();
        flight1.setDestination(flight.getDestination());
        flight1.setSource(flight.getSource());
        flight1.setArrivalTime(flight.getArrivalTime());
        flight1.setDepartureTime(flight.getDepartureTime());
        return flightRepository.save(flight1);
    }

    //Delete a complete flight...
    @DeleteMapping("/admin/{flightId}")
    public void deleteFlight(
            @PathVariable("flightId") Long id
    ) {
        boolean exists = flightRepository.existsById(id);
        if (exists) {
            flightRepository.deleteById(id);
        }
    }
}
