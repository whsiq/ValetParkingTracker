package com.valetparkingtracker.enterprise.service;

import com.valetparkingtracker.enterprise.dto.Customer;

import java.util.List;

public interface ICustomerService {
    /**
     * Fetch a cusomter with a given ID
     * @param id a unique identifier for a particular customer
     * @return the matching customer, or null if no matches found.
     */
    Customer fetchById(int id);

    void delete(int id) throws Exception;

    Customer save(Customer customer) throws Exception;

    List<Customer> fetchAll();
}
