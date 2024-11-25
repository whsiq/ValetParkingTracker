package com.valetparkingtracker.enterprise.dao;

import com.valetparkingtracker.enterprise.dto.Customer;

import java.util.List;

public interface ICustomerDAO {
    Customer save(Customer customer) throws Exception;

    List<Customer> fetchAll();

    Customer fetchByName(String firstName);

    Customer fetch(int id);

    void delete(int id);
}
