package com.assem.FlightBookingSystem.Repository;

import com.assem.FlightBookingSystem.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM tickets WHERE flight_id =:flight_Id AND price <=:requiredValue AND is_reserved = false",nativeQuery = true)
    Ticket searchByValue(
            @Param("flight_Id") Long id,
            @Param("requiredValue") double val);
}
