package com.valetparkingtracker.enterprise.dto;

import lombok.Data;

@Data
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
}
