package com.valetparkingtracker.enterprise;

import com.valetparkingtracker.enterprise.dto.Vehicle;
import com.valetparkingtracker.enterprise.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    IVehicleService vehicleService;

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

    @GetMapping("/vehicle")
    @ResponseBody
    public List<Vehicle> fetchAllVehicles() {
        vehicleService.fetchAll();
        return vehicleService.fetchAll();
    }

    /**
     *
     * @param id a unique identifier for this vehicle
     * @return
     */
    @GetMapping("/vehicle/{id}/")
    public ResponseEntity fetchVehicleById(@PathVariable("id") String id) {
        Vehicle foundVehicle = vehicleService.fetchById(Integer.parseInt(id));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundVehicle, headers, HttpStatus.OK);
    }

    /**
     * Creates a new Vehicle object given provided data
     * For some reason this works in debug mode when slowly moving through to the DAO,
     * but when a post is sent in normal runtime I'm getting a thymeleaf TemplateInputException
     *
     * Returns one of the following status codes:
     * 201: successfully created a new vehicle
     * 409: unable to create vehicle because it already exists
     *
     * @param vehicle a JSON representation of a vehicle object
     * @return the newly created vehicle object
     */
    @PostMapping(value="/vehicle", consumes="application/json", produces="application/json")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle newVehicle = null;
        try {
            newVehicle = vehicleService.save(vehicle);
        } catch (Exception e) {
            // TODO Add logging
        }
        return newVehicle;
    }

    @DeleteMapping("/vehicle/{id}/")
    public ResponseEntity deleteVehicle(@PathVariable("id") String id) {
        try {
            vehicleService.delete(Integer.parseInt(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping("/saveVehicle")
    public String saveVehicle(Vehicle vehicle) {
        try {
            vehicleService.save(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
            return "start";
        }
        return "start";
    }

    /**
     * <p>UI Mapping</p>
     *
     * Displays the form to add a new vehicle
     *
     * @return the newVehicle html page
     */
    @RequestMapping("/newVehicle")
    public String addVehicle(Model model) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId("0");
        vehicle.setFirstName("Chase");
        vehicle.setLastName("Staggs");
        vehicle.setMake("VW");
        vehicle.setModel("Golf");
        vehicle.setColor("Silver");
        vehicle.setParkingSpot("4a");
        vehicle.setNotes("damaged");
        model.addAttribute(vehicle);
        return "newVehicle";
    }
}
