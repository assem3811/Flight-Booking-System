package com.assem.FlightBookingSystem.Controller;


import com.assem.FlightBookingSystem.Model.Customer;
import com.assem.FlightBookingSystem.Repository.CustomerRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/customers")
public class CustomerController {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/all")
    @JsonIgnore
    ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>((List<Customer>) customerRepository.findAll(), HttpStatus.OK);
    }

    //Get customer by Id...
    @GetMapping("{customerId}")
    Customer getCustomerById(
            @PathVariable("customerId") Long id
    ) {
        Customer customer = customerRepository.findById(id).get();
        return customer;
    }

    //Add new Customer
    @PostMapping("/addCustomer")
    ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
    }

    //Update customer details...
    @PutMapping("{customerId}")
    Customer updateCustomer(
            @PathVariable("customerId") Long id,
            @RequestBody Customer customer
            ) {
        Customer customer1 = customerRepository.findById(id).get();
        customer1.setName(customer.getName());
        return customerRepository.save(customer1);
    }

    //Delete a Customer...
    @DeleteMapping("{customerId}")
    public void deleteCustomer(
            @PathVariable("customerId") Long id
    ){
        boolean exists = customerRepository.existsById(id);
        if(exists) {
            customerRepository.deleteById(id);
        }
    }
}
