package com.assem.FlightBookingSystem.Controller;


import com.assem.FlightBookingSystem.Model.ReservedTicket;
import com.assem.FlightBookingSystem.Model.Ticket;
import com.assem.FlightBookingSystem.Repository.ReservedTicketRepository;
import com.assem.FlightBookingSystem.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/reserve")
public class ReservedTicketController {

    @Autowired
    private final ReservedTicketRepository reservedTicketRepository;
    private final TicketRepository ticketRepository;

    public ReservedTicketController(ReservedTicketRepository reservedTicketRepository, TicketRepository ticketRepository) {
        this.reservedTicketRepository = reservedTicketRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("all")
    List<ReservedTicket> getAllReservedTickets(){
        return reservedTicketRepository.findAll();
    }

    @PostMapping("/book/{ticketId}")
    void reserveTicket(
            @PathVariable("ticketId") Long id,
            @RequestBody ReservedTicket reservedTicket
    ){
        Ticket ticket = ticketRepository.findById(id).get();
        ticket.setReserved(true);
        ticketRepository.save(ticket);
        reservedTicketRepository.save(reservedTicket);
    }
//PUT REQUEST EXAMPLE
//    {
//        "customer": {
//        "id": 1
//    },
//        "ticket": {
//        "id": 1
//    }
//    }

    @PutMapping("{reservationId}/{requiredTicketId}")
    void updateTicketClass(
            @PathVariable("reservationId") Long id,
            @PathVariable("requiredTicketId") Long reqTickId,
            @RequestBody ReservedTicket reservedTicket
    ) {
        ReservedTicket reservedTicket1 = reservedTicketRepository.findById(id).get();
        Long ticketId = Long.valueOf(reservedTicket1.getTicket().getId());
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setReserved(false);
        ticketRepository.save(ticket);
        reservedTicketRepository.deleteById(id);
        //Till here its all good....
        Ticket reqTicket = ticketRepository.findById(reqTickId).get();
        reqTicket.setReserved(true);
        ticketRepository.save(reqTicket);
        reservedTicketRepository.save(reservedTicket);
    }

    @DeleteMapping("cancel/{reservationId}")
    void cancelReservation(
            @PathVariable("reservationId") Long id
    ) {
        ReservedTicket reservedTicket = reservedTicketRepository.findById(id).get();
        Long ticketId = Long.valueOf(reservedTicket.getTicket().getId());
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setReserved(false);
        ticketRepository.save(ticket);
        reservedTicketRepository.deleteById(id);
    }

}
