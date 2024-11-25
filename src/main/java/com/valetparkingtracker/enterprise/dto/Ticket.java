package com.valetparkingtracker.enterprise.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// Ticket can likely be a JSON in/out object - think of the objects being sent from valet computer to front desk computer , or backup to valet computer
// give properties @SerializedName tag

@Entity
public @Data class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketId;
    private Customer customer;
    private Vehicle vehicle;
    private String parkingSpot;
}
