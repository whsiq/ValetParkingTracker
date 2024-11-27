package com.valetparkingtracker.enterprise.dao;

import com.valetparkingtracker.enterprise.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("customerDAO")
public class CustomerDAO implements ICustomerDAO {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) throws Exception {
        Customer createdCustomer = customerRepository.save(customer);
        return createdCustomer;
    }

    @Override
    public List<Customer> fetchAll() {
        List<Customer> allCustomers = new ArrayList<>();
        Iterable<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    @Override
    public Customer fetch(int id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(id);
    }
}
