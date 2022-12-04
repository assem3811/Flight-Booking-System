package com.assem.FlightBookingSystem.Model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "customer_id_seq",
            sequenceName = "customer_id_seq",
            allocationSize = 1
    )
    private int id;
    @Column(nullable = false)
    private String name;

    @JsonManagedReference(value = "customer")
    @OneToMany(mappedBy = "customer")
    List<ReservedTicket> reservedTickets;


    public Customer() {
    }

    public Customer(int id, String name, List<ReservedTicket> reservedTickets) {
        this.id = id;
        this.name = name;
        this.reservedTickets = reservedTickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReservedTicket> getReservedTickets() {
        return reservedTickets;
    }

    public void setReservedTickets(List<ReservedTicket> reservedTickets) {
        this.reservedTickets = reservedTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(reservedTickets, customer.reservedTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, reservedTickets);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reservedTickets=" + reservedTickets +
                '}';
    }
}
