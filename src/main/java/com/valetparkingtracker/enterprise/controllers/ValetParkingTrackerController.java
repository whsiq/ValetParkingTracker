package com.valetparkingtracker.enterprise.controllers;

import com.valetparkingtracker.enterprise.dto.Customer;
import com.valetparkingtracker.enterprise.dto.Ticket;
import com.valetparkingtracker.enterprise.dto.Vehicle;
import com.valetparkingtracker.enterprise.service.ITicketService;
import com.valetparkingtracker.enterprise.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

    private static final Logger logger = LoggerFactory.getLogger(ValetParkingTrackerController.class);

    /**
     * Handle the root (/) endpoint and return a start page.
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        List<Ticket> tickets = ticketService.fetchAll();
        model.addAttribute("tickets", tickets);

        return "start";
    }

    @RequestMapping("/saveTicket")
    public String saveTicket(Model model, Ticket ticket) {
        try {
            ticketService.save(ticket);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("An error occurred while saving the ticket with id {}", ticket.getTicketId(), e);
            return index(model);
        }
        return index(model);
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
        Customer customer = new Customer();
        Vehicle vehicle = new Vehicle();

        ticket.setCustomer(customer);
        ticket.setVehicle(vehicle);
        model.addAttribute(ticket);
        return "checkIn";
    }

    /**
     * <p>UI Mapping</p>
     *
     * Displays the form to check in a guest
     * Should create a new vehicle, customer, and ticket using provided info
     *
     * @return the newVehicle html page
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        Ticket ticket = ticketService.fetchById(Integer.parseInt(id));
        if (ticket == null) {
            logger.error("Ticket with id {} not found", id);
            return index(model);
        }
        model.addAttribute(ticket);
        return "edit";
    }

    @RequestMapping("/pullVehicle/{id}")
    public String pullVehicle(@PathVariable("id") String id, Model model) {
        try {
            ticketService.delete(Integer.parseInt(id));
            return index(model);
        } catch (Exception e) {
            logger.error("An error occurred while pulling the vehicle with id {}", id, e);
            return index(model);
        }
    }
}
