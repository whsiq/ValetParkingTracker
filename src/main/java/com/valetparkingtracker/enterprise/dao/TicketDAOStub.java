package com.valetparkingtracker.enterprise.dao;

import com.valetparkingtracker.enterprise.dto.Customer;
import com.valetparkingtracker.enterprise.dto.Ticket;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TicketDAOStub implements ITicketDAO {

    Map<Integer, Ticket> allTickets = new HashMap<>();

    @Override
    public Ticket save(Ticket ticket) throws Exception {
        Integer ticketID = ticket.getTicketId();
        allTickets.put(ticketID, ticket);
        return ticket;
    }

    @Override
    public List<Ticket> fetchAll() {
        return new ArrayList<>(allTickets.values());
    }

    @Override
    public Ticket fetch(int id) {
        return allTickets.get(id);
    }

    @Override
    public void delete(int id) {
        allTickets.remove(id);
    }
}
