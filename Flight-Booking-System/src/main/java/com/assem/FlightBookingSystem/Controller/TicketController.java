package com.assem.FlightBookingSystem.Controller;


import com.assem.FlightBookingSystem.Model.Ticket;
import com.assem.FlightBookingSystem.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketController {

    @Autowired
    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    //Search by Value...
    @GetMapping("{flight_id}/{value}")
    Ticket searchTicketByValue(
            @PathVariable("flight_id") Long id,
            @PathVariable("value") double val
    ) {
        return ticketRepository.searchByValue(id, val);
    }
}
