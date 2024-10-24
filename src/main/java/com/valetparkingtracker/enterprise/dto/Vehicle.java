package com.valetparkingtracker.enterprise.dto;

import lombok.Data;

public @Data
class Vehicle {
    private int vehicleId;
    private String make;
    private String model;
    private String color;
    private String notes;
}
