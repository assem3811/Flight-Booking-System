package com.assem.FlightBookingSystem.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reserved_tickets")
public class ReservedTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "res_id_seq",
            sequenceName = "res_id_seq",
            allocationSize = 1
    )
    private int resId;

    @JsonBackReference("customer")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonManagedReference(value = "reservedTicket")
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public ReservedTicket() {
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservedTicket that = (ReservedTicket) o;
        return resId == that.resId && Objects.equals(customer, that.customer) && Objects.equals(ticket, that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resId, customer, ticket);
    }

    @Override
    public String toString() {
        return "ReservedTicket{" +
                "resId=" + resId +
                ", customer=" + customer +
                ", ticket=" + ticket +
                '}';
    }
}
