package com.valetparkingtracker.enterprise.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
public @Data
class Vehicle {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int vehicleId;
    private String make;
    private String model;
    private String color;
    private String notes;
}
