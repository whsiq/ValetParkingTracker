package com.valetparkingtracker.enterprise.dao;

import com.valetparkingtracker.enterprise.dto.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerDAOStub implements ICustomerDAO {

    Map<Integer, Customer> allCustomers = new HashMap<>();

    @Override
    public Customer save(Customer customer) throws Exception {
        Integer customerID = customer.getCustomerId();
        allCustomers.put(customerID, customer);
        return customer;
    }

    @Override
    public List<Customer> fetchAll() {
        List<Customer> returnCustomers = new ArrayList(allCustomers.values());
        return returnCustomers;
    }

    @Override
    public Customer fetch(int id) {
        return allCustomers.get(id);
    }

    @Override
    public void delete(int id) {
        allCustomers.remove(id);
    }
}
