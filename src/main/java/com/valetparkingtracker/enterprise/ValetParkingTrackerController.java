package com.valetparkingtracker.enterprise;

import com.valetparkingtracker.enterprise.dto.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * Handle the root (/) endpoint and return a start page.
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        Vehicle vehicle = new Vehicle();
        vehicle.setFirstName("Chase");
        vehicle.setLastName("Staggs");
        vehicle.setMake("VW");
        vehicle.setModel("Golf");
        vehicle.setColor("Silver");
        vehicle.setParkingSpot("4a");
        model.addAttribute(vehicle);
        return "start";
    }

    /**
     * Should eventually save a vehicle to the database and return to the start page
     * @param vehicle the vehicle to be saved with the provided information
     * @return
     */
    @RequestMapping("/saveVehicle")
    public String saveVehicle(Vehicle vehicle) {
        return "start";
    }

    /**
     * Displays the form to add a new vehicle
     *
     * @return the newVehicle html page
     */
    @RequestMapping("/newVehicle")
    public String addVehicle(Model model) {
        Vehicle vehicle = new Vehicle();
        model.addAttribute(vehicle);
        return "newVehicle";
    }

    /**
     * Creates a new Vehicle object given provided data
     *
     * Returns one of the following status codes:
     * 201: successfully created a new specimen
     * 409: unable to create specimen because it already exists
     *
     * @param vehicle a JSON representation of a vehicle object
     * @return the newly created vehicle object
     */
    @PostMapping(value = "/createVehicle", consumes = "application/json", produces = "application/json")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicle;
    }
}
