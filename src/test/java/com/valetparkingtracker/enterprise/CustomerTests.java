package com.valetparkingtracker.enterprise;

import com.valetparkingtracker.enterprise.dao.ICustomerDAO;
import com.valetparkingtracker.enterprise.dto.Customer;
import com.valetparkingtracker.enterprise.service.ICustomerService;
import com.valetparkingtracker.enterprise.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class CustomerTests {

    @Autowired
    private ICustomerService customerService;
    private Customer customer = new Customer();

    @Qualifier("customerDAOStub")
    @MockBean
    private ICustomerDAO customerDAO;

    @Test
    void fetchCustomerByID_returnsVolkswagenForID25() throws Exception {
        givenCustomerDataIsAvailable();
        whenCustomer25AddedIsBob();
        whenSearchCustomerWithID25();
        thenReturnOneBobCustomerForID25();
    }

    private void givenCustomerDataIsAvailable() throws Exception {
        Mockito.when(customerDAO.save(customer)).thenReturn(customer);
        customerService = new CustomerService(customerDAO);
    }

    private void whenCustomer25AddedIsBob() {
        Customer bob = new Customer();
        bob.setCustomerId(25);
        bob.setFirstName("Bob");
        Mockito.when(customerDAO.fetch(25)).thenReturn(bob);
    }

    private void whenSearchCustomerWithID25() {
        customer = customerService.fetchById(25);
    }

    private void thenReturnOneBobCustomerForID25() {
        String FirstName = customer.getFirstName();
        assertEquals("Bob", FirstName);
    }

    @Test
    void saveCustomer_validateReturnCustomerWithFirstAndLastName() throws Exception {
        givenCustomerDataIsAvailable();
        whenUserCreatesANewCustomerAndSaves();
        thenCreateNewCustomerRecordAndReturnIt();
    }

    private void whenUserCreatesANewCustomerAndSaves() {
        customer.setFirstName("Bob");
        customer.setLastName("Doe");
    }

    private void thenCreateNewCustomerRecordAndReturnIt() throws Exception {
        Customer createdCustomer = customerService.save(customer);
        assertEquals(customer, createdCustomer);
        verify(customerDAO, atLeastOnce()).save(customer);
    }

    @Test
    void deleteCustomer_validateMethod() throws Exception {
        givenCustomerDataIsAvailable();
        whenCustomer25AddedIsBob();
        whenSearchCustomerWithID25();
        thenDeleteCustomerWithID25();
        thenValidateCustomerWithID25CustomerNotFound();
    }

    private void thenDeleteCustomerWithID25() throws Exception {
        customerService.delete(25);
        verify(customerDAO, atLeastOnce()).delete(25);
    }

    private void thenValidateCustomerWithID25CustomerNotFound() {
        Mockito.when(customerDAO.fetch(25)).thenReturn(null);
        verify(customerDAO, atLeastOnce()).delete(25);
    }

    @Test
    void fetchAllCustomers_validateMethod() throws Exception {
        givenCustomerDataIsAvailable();
        whenMultipleCustomersAdded();
        thenReturnAllCustomers();
    }

    private void whenMultipleCustomersAdded() {
        List<Customer> mockCusomers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setFirstName("Sam");

        Customer customer2 = new Customer();
        customer2.setFirstName("Williams");

        mockCusomers.add(customer1);
        mockCusomers.add(customer2);

        Mockito.when(customerDAO.fetchAll()).thenReturn(mockCusomers);
    }

    private void thenReturnAllCustomers() {
        List<Customer> result = customerService.fetchAll();
        assertEquals(2, result.size());
        assertEquals("Sam", result.get(0).getFirstName());
        assertEquals("Williams", result.get(1).getFirstName());
        verify(customerDAO, atLeastOnce()).fetchAll();
    }

}
