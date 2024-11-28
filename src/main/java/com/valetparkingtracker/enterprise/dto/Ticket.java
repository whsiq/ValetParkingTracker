package com.valetparkingtracker.enterprise.dto;

import jakarta.persistence.*;
import lombok.Data;

// Ticket can likely be a JSON in/out object - think of the objects being sent from valet computer to front desk computer , or backup to valet computer
// give properties @SerializedName tag

@Entity
public @Data class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketId;
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicleId")
    private Vehicle vehicle;
    private String parkingSpot;
}
