package com.valetparkingtracker.enterprise;

import com.valetparkingtracker.enterprise.dao.ITicketDAO;
import com.valetparkingtracker.enterprise.dto.Ticket;
import com.valetparkingtracker.enterprise.service.TicketService;
import com.valetparkingtracker.enterprise.service.ITicketService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class TicketTests {

    @Autowired
    private ITicketService ticketService;
    private Ticket ticket = new Ticket();

    @Qualifier("ticketDAOStub")
    @MockBean
    private ITicketDAO ticketDAO;

    @Test
    void fetchTicketByID_returnsParkingSpotForID25() throws Exception {
        givenTicketDataIsAvailable();
        whenTicket25AddedIsBob();
        whenSearchTicketWithID25();
        thenReturnOneBobTicketForID25();
    }

    private void givenTicketDataIsAvailable() throws Exception {
        Mockito.when(ticketDAO.save(ticket)).thenReturn(ticket);
        ticketService = new TicketService(ticketDAO);
    }

    private void whenTicket25AddedIsBob() {
        Ticket spot = new Ticket();
        spot.setTicketId(25);
        spot.setParkingSpot("#10");
        Mockito.when(ticketDAO.fetch(25)).thenReturn(spot);
    }

    private void whenSearchTicketWithID25() {
        ticket = ticketService.fetchById(25);
    }

    private void thenReturnOneBobTicketForID25() {
        String ParkingSpot = ticket.getParkingSpot();
        assertEquals("#10", ParkingSpot);
    }

    @Test
    void saveTicket_validateReturnTicketWithFirstAndLastName() throws Exception {
        givenTicketDataIsAvailable();
        whenUserCreatesANewTicketAndSaves();
        thenCreateNewTicketRecordAndReturnIt();
    }

    private void whenUserCreatesANewTicketAndSaves() {
        ticket.setParkingSpot("#50");
    }

    private void thenCreateNewTicketRecordAndReturnIt() throws Exception {
        Ticket createdTicket = ticketService.save(ticket);
        assertEquals(ticket, createdTicket);
        verify(ticketDAO, atLeastOnce()).save(ticket);
    }

    @Test
    void deleteTicket_validateMethod() throws Exception {
        givenTicketDataIsAvailable();
        whenTicket25AddedIsBob();
        whenSearchTicketWithID25();
        thenDeleteTicketWithID25();
        thenValidateTicketWithID25TicketNotFound();
    }

    private void thenDeleteTicketWithID25() throws Exception {
        ticketService.delete(25);
        verify(ticketDAO, atLeastOnce()).delete(25);
    }

    private void thenValidateTicketWithID25TicketNotFound() {
        Mockito.when(ticketDAO.fetch(25)).thenReturn(null);
        verify(ticketDAO, atLeastOnce()).delete(25);
    }

    @Test
    void fetchAllTickets_validateMethod() throws Exception {
        givenTicketDataIsAvailable();
        whenMultipleTicketsAdded();
        thenReturnAllTickets();
    }

    private void whenMultipleTicketsAdded() {
        List<Ticket> mockTickets = new ArrayList<>();
        Ticket ticket1 = new Ticket();
        ticket1.setParkingSpot("#45");

        Ticket ticket2 = new Ticket();
        ticket2.setParkingSpot("#23");

        mockTickets.add(ticket1);
        mockTickets.add(ticket2);

        Mockito.when(ticketDAO.fetchAll()).thenReturn(mockTickets);
    }

    private void thenReturnAllTickets() {
        List<Ticket> result = ticketService.fetchAll();
        assertEquals(2, result.size());
        assertEquals("#45", result.get(0).getParkingSpot());
        assertEquals("#23", result.get(1).getParkingSpot());
        verify(ticketDAO, atLeastOnce()).fetchAll();
    }
}
