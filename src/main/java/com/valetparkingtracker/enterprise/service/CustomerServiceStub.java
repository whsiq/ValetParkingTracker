package com.valetparkingtracker.enterprise.service;

import com.valetparkingtracker.enterprise.dao.ICustomerDAO;
import com.valetparkingtracker.enterprise.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceStub implements ICustomerService {

    @Autowired
    private ICustomerDAO customerDAO;

    public CustomerServiceStub() {

    }

    public CustomerServiceStub(ICustomerDAO CustomerDAO) {

        this.customerDAO = CustomerDAO;
    }

    @Override
    public Customer fetchById(int id) {
        Customer foundCustomer = customerDAO.fetch(id);
        return foundCustomer;
    }

    @Override
    public void delete(int id) throws Exception {
        customerDAO.delete(id);
    }

    @Override
    public Customer save(Customer customer) throws Exception {
        return customerDAO.save(customer);
    }

    @Override
    public List<Customer> fetchAll() {
        return customerDAO.fetchAll();
    }

    public Customer fetchByName(String firstName)
    {
        Customer foundCustomer = customerDAO.fetchByName(firstName);
        return foundCustomer;
    }
}
