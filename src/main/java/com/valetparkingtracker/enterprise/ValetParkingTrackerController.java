package com.valetparkingtracker.enterprise;

import com.valetparkingtracker.enterprise.dto.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
