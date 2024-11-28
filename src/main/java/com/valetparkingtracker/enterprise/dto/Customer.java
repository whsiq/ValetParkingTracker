package com.valetparkingtracker.enterprise.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
public @Data
class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int customerId;
    private String firstName;
    private String lastName;
}
