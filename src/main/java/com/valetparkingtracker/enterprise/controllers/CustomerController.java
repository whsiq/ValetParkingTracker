package com.valetparkingtracker.enterprise.controllers;

import com.valetparkingtracker.enterprise.dto.Customer;
import com.valetparkingtracker.enterprise.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *     This class handles the CRUD operations for Vehicles through HTTP actions
 * </p>
 */
@Controller
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @GetMapping("/customer")
    @ResponseBody
    public List<Customer> fetchAllCustomers() {
        customerService.fetchAll();
        return customerService.fetchAll();
    }

    /**
     *
     * @param id a unique identifier for this customer
     * @return
     */
    @GetMapping("/customer/{id}/")
    public ResponseEntity fetchCustomerById(@PathVariable("id") String id) {
        Customer foundCustomer = customerService.fetchById(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundCustomer, headers, HttpStatus.OK);
    }

    /**
     * Creates a new Customer object given provided data
     * For some reason this works in debug mode when slowly moving through to the DAO,
     * but when a post is sent in normal runtime I'm getting a thymeleaf TemplateInputException
     *
     * Returns one of the following status codes:
     * 201: successfully created a new customer
     * 409: unable to create customer because it already exists
     *
     * @param customer a JSON representation of a customer object
     * @return the newly created customer object
     */
    @PostMapping(value="/customer", consumes="application/json", produces="application/json")
    public Customer createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = null;
        try {
            newCustomer = customerService.save(customer);
        } catch (Exception e) {
            // TODO Add logging
        }
        return newCustomer;
    }

    @DeleteMapping("/customer/{id}/")
    public ResponseEntity deleteCustomer(@PathVariable("id") String id) {
        try {
            customerService.delete(Integer.parseInt(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
