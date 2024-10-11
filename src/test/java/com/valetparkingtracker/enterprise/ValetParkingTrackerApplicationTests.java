package com.valetparkingtracker.enterprise;

import com.valetparkingtracker.enterprise.dto.Vehicle;
import com.valetparkingtracker.enterprise.service.IVehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ValetParkingTrackerApplicationTests {

    @Autowired
    private IVehicleService vehicleService;
    private Vehicle vehicle;

    @Test
    void contextLoads() {
    }

    @Test
    void fetchVehicleByID_returnsVolkswagenForID25() {
        givenVehicleDataIsAvailable();
        whenSearchVehicleWithID25();
        thenReturnOneVolkswagenVehicleForID25();

    }

    private void givenVehicleDataIsAvailable() {
    }

    private void whenSearchVehicleWithID25() {
        vehicle = vehicleService.fetchById(25);
    }

    private void thenReturnOneVolkswagenVehicleForID25() {
        String description = vehicle.getMake();
        assertEquals("Volkswagen", description);
    }
}
