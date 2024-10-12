package com.valetparkingtracker.enterprise.dto;

import lombok.Data;

public @Data
class Vehicle {
    private String id;
    private String firstName;
    private String lastName;
    private String make;
    private String model;
    private String color;
    private String parkingSpot;
    private String notes;
}
