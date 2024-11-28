package com.valetparkingtracker.enterprise.service;

import com.valetparkingtracker.enterprise.dao.ICustomerDAO;
import com.valetparkingtracker.enterprise.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerDAO customerDAO;

    public CustomerService() {

    }

    public CustomerService(ICustomerDAO CustomerDAO) {

        this.customerDAO = CustomerDAO;
    }

    @Override
    @Cacheable(value = "customer", key = "#id")
    public Customer fetchById(int id) {
        Customer foundCustomer = customerDAO.fetch(id);
        return foundCustomer;
    }

    @Override
    @CacheEvict(value = "customer", key = "#id")
    public void delete(int id) throws Exception {
        customerDAO.delete(id);
    }

    @Override
    public Customer save(Customer customer) throws Exception {
        return customerDAO.save(customer);
    }

    @Override
    @Cacheable("customers")
    public List<Customer> fetchAll() {
        return customerDAO.fetchAll();
    }
}
