package com.valetparkingtracker.enterprise.controllers;

import com.valetparkingtracker.enterprise.dto.Customer;
import com.valetparkingtracker.enterprise.dto.Ticket;
import com.valetparkingtracker.enterprise.dto.Vehicle;
import com.valetparkingtracker.enterprise.service.ICustomerService;
import com.valetparkingtracker.enterprise.service.ITicketService;
import com.valetparkingtracker.enterprise.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    IVehicleService vehicleService;
    @Autowired
    ICustomerService customerService;

    List<Ticket> tickets;

    /**
     * Handle the root (/) endpoint and return a start page.
     *
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        tickets = ticketService.fetchAll();
        model.addAttribute("tickets", tickets);

        return "start";
    }

    @RequestMapping("/saveTicket")
    public String saveTicket(Model model, Ticket ticket) {
        try {
            customerService.save(ticket.getCustomer());
            vehicleService.save(ticket.getVehicle());
            ticketService.save(ticket);
            return index(model);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    /**
     * <p>UI Mapping</p>
     * <p>
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
     * <p>
     * Displays the form to check in a guest
     * Should create a new vehicle, customer, and ticket using provided info
     *
     * @return the newVehicle html page
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        Ticket ticket = ticketService.fetchById(Integer.parseInt(id));
        model.addAttribute(ticket);
        return "edit";
    }



    @RequestMapping("/pullVehicle/{id}")
    public String pullVehicle(@PathVariable("id") String id, Model model) {
        try {
            ticketService.delete(Integer.parseInt(id));
            return index(model);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * <p>Request Vehicle Page</p>
     * <p>
     * Displays a form for a guest to request their vehicle
     * Should pull an already listed vehicle using its ticket number
     *
     * @return the home page
     */


    @RequestMapping("/requestVehicle")
    public String requestVehicle(Model model) {
        return "requestVehicle";
    }

    @RequestMapping(value = "/pullRequestedVehicle", method = RequestMethod.POST)
    public String pullRequestedVehicle(@RequestParam("ticketId") String ticketId, Model model) {
        try {
            ticketService.delete(Integer.parseInt(ticketId));
            return index(model);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}


