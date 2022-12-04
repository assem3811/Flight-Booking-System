package com.assem.FlightBookingSystem.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;


enum ticketClass {A, B, C};
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "ticket_id_seq",
            sequenceName = "ticket_id_seq",
            allocationSize = 1
    )
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "flightClass")
    ticketClass tClass;

    @Column(nullable = false)
    private double price;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(columnDefinition = "boolean default false")
    private boolean isReserved;

    @JsonBackReference(value = "reservedTicket")
    @OneToOne(mappedBy = "ticket")
    private ReservedTicket reservedTicket;

    public Ticket() {
    }

    public Ticket(
            int id,
            ticketClass tClass,
            double price,
            Flight flight,
            boolean isReserved,
            ReservedTicket reservedTicket) {
        this.id = id;
        this.tClass = tClass;
        this.price = price;
        this.flight = flight;
        this.isReserved = isReserved;
        this.reservedTicket = reservedTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ticketClass gettClass() {
        return tClass;
    }

    public void settClass(ticketClass tClass) {
        this.tClass = tClass;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public ReservedTicket getReservedTicket() {
        return reservedTicket;
    }

    public void setReservedTicket(ReservedTicket reservedTicket) {
        this.reservedTicket = reservedTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Double.compare(ticket.price, price) == 0 && isReserved == ticket.isReserved && tClass == ticket.tClass && Objects.equals(flight, ticket.flight) && Objects.equals(reservedTicket, ticket.reservedTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tClass, price, flight, isReserved, reservedTicket);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", tClass=" + tClass +
                ", price=" + price +
                ", flight=" + flight +
                ", isReserved=" + isReserved +
                ", reservedTicket=" + reservedTicket +
                '}';
    }
}
