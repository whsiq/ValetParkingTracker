package com.valetparkingtracker.enterprise.controllers;

import com.valetparkingtracker.enterprise.dto.Customer;
import com.valetparkingtracker.enterprise.dto.Ticket;
import com.valetparkingtracker.enterprise.dto.Vehicle;
import com.valetparkingtracker.enterprise.service.ITicketService;
import com.valetparkingtracker.enterprise.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * The controller for Valet Parking Tracker REST endpoints and web UI
 * <p>
 *     This class handles the CRUD operations for Valet Parking Tracker vehicles through HTTP actions
 * </p>
 * <p>
 *     This class also serves HTML pages for UI interactions with vehicles
 * </p>
 */
@Controller
public class ValetParkingTrackerController {

    @Autowired
    ITicketService ticketService;

    /**
     * Handle the root (/) endpoint and return a start page.
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        Ticket ticket = new Ticket();
//        Customer customer = new Customer();
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("VW");
        vehicle.setModel("Golf");
        vehicle.setColor("Silver");
        vehicle.setNotes("damaged");
        ticket.setVehicle(vehicle);
        ticket.setParkingSpot("4a");
        model.addAttribute(ticket);
        return "start";
    }

    @RequestMapping("/saveVehicle")
    public String saveVehicle(Ticket ticket) {
        try {
            ticketService.save(ticket);
        } catch (Exception e) {
            e.printStackTrace();
            return "start";
        }
        return "start";
    }

    /**
     * <p>UI Mapping</p>
     *
     * Displays the form to check in a guest
     * Should create a new vehicle, customer, and ticket using provided info
     *
     * @return the newVehicle html page
     */
    @RequestMapping("/checkIn")
    public String checkIn(Model model) {
        Ticket ticket = new Ticket();
//        Customer customer = new Customer();
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("VW");
        vehicle.setModel("Golf");
        vehicle.setColor("Silver");
        vehicle.setNotes("damaged");
        ticket.setVehicle(vehicle);
        ticket.setParkingSpot("4a");
        model.addAttribute(ticket);
        return "checkIn";
    }
}
