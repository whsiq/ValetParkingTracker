package com.valetparkingtracker.enterprise;

import com.valetparkingtracker.enterprise.dao.IVehicleDAO;
import com.valetparkingtracker.enterprise.dto.Vehicle;
import com.valetparkingtracker.enterprise.service.IVehicleService;
import com.valetparkingtracker.enterprise.service.VehicleServiceStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class VehicleTests {

    @Autowired
    private IVehicleService vehicleService;
    private Vehicle vehicle;

    @MockBean
    private IVehicleDAO vehicleDAO;

    @BeforeEach
    public void setUp() {
        // Initialize the vehicle object before each test
        vehicle = new Vehicle();
    }

    @Test
    void fetchVehicleByID_returnsVolkswagenForID25() throws Exception {
        givenVehicleDataIsAvailable();
        whenVehicle25AddedIsVolkswagen();
        whenSearchVehicleWithID25();
        thenReturnOneVolkswagenVehicleForID25();
    }

    private void givenVehicleDataIsAvailable() throws Exception {
        Mockito.when(vehicleDAO.save(vehicle)).thenReturn(vehicle);
        vehicleService = new VehicleServiceStub(vehicleDAO);
    }

    private void whenVehicle25AddedIsVolkswagen() {
        Vehicle volkswagen = new Vehicle();
        volkswagen.setVehicleId("25");
        volkswagen.setMake("Volkswagen");
        Mockito.when(vehicleDAO.fetch(25)).thenReturn(volkswagen);
    }

    private void whenSearchVehicleWithID25() {
        vehicle = vehicleService.fetchById(25);
    }

    private void thenReturnOneVolkswagenVehicleForID25() {
        String make = vehicle.getMake();
        assertEquals("Volkswagen", make);
    }

    @Test
    void saveVehicle_validateReturnVehicleWithMakeAndModel() throws Exception {
        givenVehicleDataIsAvailable();
        whenUserCreatesANewVehicleAndSaves();
        thenCreateNewVehicleRecordAndReturnIt();
    }

    private void whenUserCreatesANewVehicleAndSaves() {
        vehicle.setMake("Volkswagen");
        vehicle.setModel("Golf");
    }

    private void thenCreateNewVehicleRecordAndReturnIt() throws Exception {
        Vehicle createdVehicle = vehicleService.save(vehicle);
        assertEquals(vehicle, createdVehicle);
        verify(vehicleDAO, atLeastOnce()).save(vehicle);
    }

    @Test
    void fetchVehicleByID_shouldReturnNull_whenVehicleNotFound() throws Exception {
        // Given
        Mockito.when(vehicleDAO.fetch(999)).thenReturn(null); // Simulate vehicle not found

        // When
        Vehicle fetchedVehicle = vehicleService.fetchById(999); // Attempt to fetch a non-existent vehicle

        // Then
        assertNull(fetchedVehicle); // Verify that the returned vehicle is null
    }

    @Test
    void saveVehicle_shouldThrowException_whenVehicleIsNull() {
        // Given
        Vehicle nullVehicle = null;

        // When / Then
        Exception exception = assertThrows(Exception.class, () -> {
            vehicleService.save(nullVehicle); // Attempt to save a null vehicle
        });
        assertEquals("Vehicle cannot be null", exception.getMessage()); // Verify the expected exception message
    }
}
