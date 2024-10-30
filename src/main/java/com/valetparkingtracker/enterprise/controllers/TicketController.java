package com.valetparkingtracker.enterprise.controllers;

import com.valetparkingtracker.enterprise.dto.Customer;
import com.valetparkingtracker.enterprise.dto.Ticket;
import com.valetparkingtracker.enterprise.dto.Vehicle;
import com.valetparkingtracker.enterprise.service.ICustomerService;
import com.valetparkingtracker.enterprise.service.ITicketService;
import com.valetparkingtracker.enterprise.service.IVehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class TicketController {
    @Autowired
    ITicketService ticketService;

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @GetMapping("/ticket")
    @ResponseBody
    public List<Ticket> fetchAllTickets() {
        ticketService.fetchAll();
        return ticketService.fetchAll();
    }

    /**
     *
     * @param id a unique identifier for this ticket
     * @return
     */
    @GetMapping("/ticket/{id}/")
    public ResponseEntity fetchTicketById(@PathVariable("id") String id) {
        Ticket foundTicket = ticketService.fetchById(Integer.parseInt(id));
        if (foundTicket == null) {
            logger.error("Ticket with id {} not found", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundTicket, headers, HttpStatus.OK);
    }

    /**
     * Creates a new Vehicle object given provided data
     * For some reason this works in debug mode when slowly moving through to the
     * DAO,
     * but when a post is sent in normal runtime I'm getting a thymeleaf
     * TemplateInputException
     *
     * Returns one of the following status codes:
     * 201: successfully created a new vehicle
     * 409: unable to create vehicle because it already exists
     *
     * @param ticket a JSON representation of a vehicle object
     * @return the newly created vehicle object
     */
    @PostMapping(value = "/ticket", consumes = "application/json", produces = "application/json")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        Ticket newTicket = null;
        try {
            newTicket = ticketService.save(ticket);
        } catch (Exception e) {
            logger.error("Failed to create ticket with id {}", ticket.getTicketId());
        }
        return newTicket;
    }

    @DeleteMapping("/ticket/{id}/")
    public ResponseEntity deleteVehicle(@PathVariable("id") String id) {
        try {
            ticketService.delete(Integer.parseInt(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while deleting the ticket with id {}", id, e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
