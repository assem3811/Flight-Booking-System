package com.assem.FlightBookingSystem.Repository;

import com.assem.FlightBookingSystem.Model.Customer;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "INSERT INTO customers_flights(flight_id, customer_id) VALUES(:flightId, :customerId)", nativeQuery = true)
    String bookFlight(
            @Param("flightId") Long fId,
            @Param("customerId") Long cId
    );

}
