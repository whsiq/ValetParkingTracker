package com.valetparkingtracker.enterprise.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valetparkingtracker.enterprise.dto.Ticket;
import com.valetparkingtracker.enterprise.service.ITicketService;

@Controller
public class TicketController {
    @Autowired
    ITicketService ticketService;

    @GetMapping("/ticket")
    @ResponseBody
    public List<Ticket> fetchAllTickets() {
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
            // TODO Add logging
        }
        return newTicket;
    }

    @DeleteMapping("/ticket/{id}/")
    public ResponseEntity deleteVehicle(@PathVariable("id") String id) {
        try {
            ticketService.delete(Integer.parseInt(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
