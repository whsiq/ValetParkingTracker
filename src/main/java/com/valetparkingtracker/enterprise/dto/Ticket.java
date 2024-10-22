package com.valetparkingtracker.enterprise.dto;

import lombok.Data;

public @Data
class Ticket {
    private int ticketId;
    private Customer customer;
    private Vehicle vehicle;
    private int parkingSpot;
}
