package com.valetparkingtracker.enterprise.dto;

import lombok.Data;

@Data
public class Ticket {
    private int ticketId;
    private Customer customer;
    private Vehicle vehicle;
    private int parkingSpot;
}
