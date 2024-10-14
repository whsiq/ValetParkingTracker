package com.valetparkingtracker.enterprise.dto;

import lombok.Data;

public @Data
class Vehicle {
    private String vehicleId;
    private String make;
    private String model;
    private String color;
    private String notes;
}
