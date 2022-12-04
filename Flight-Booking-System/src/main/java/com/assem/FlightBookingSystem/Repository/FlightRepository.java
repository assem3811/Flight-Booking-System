package com.assem.FlightBookingSystem.Repository;

import com.assem.FlightBookingSystem.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT * FROM flights WHERE destination=:destination AND source=:source", nativeQuery = true)
    List<Flight> findFlightsBySrcAndDest(
            @Param("destination") String to,
            @Param("source") String from);
}
