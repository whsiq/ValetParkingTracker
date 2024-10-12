package com.valetparkingtracker.enterprise.dto;

import lombok.Data;

@Data
public class Ticket {
    private int ticketId;
    private int customerId;
    private int vehicleId;
    private int parkingSpot;
}
