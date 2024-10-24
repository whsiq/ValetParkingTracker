package com.valetparkingtracker.enterprise.dto;

import lombok.Data;

// Ticket can likely be a JSON in/out object - think of the objects being sent from valet computer to front desk computer , or backup to valet computer
// give properties @SerializedName tag
@Data
public class Ticket {
    private int ticketId;
    private Customer customer;
    private Vehicle vehicle;
    private String parkingSpot;
}
