package com.assem.FlightBookingSystem.Repository;

import com.assem.FlightBookingSystem.Model.ReservedTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedTicketRepository extends JpaRepository<ReservedTicket, Long> {
}
