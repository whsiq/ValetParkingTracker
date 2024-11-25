package com.valetparkingtracker.enterprise.dao;

import com.valetparkingtracker.enterprise.dto.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("ticketDAO")
public class TicketDAO implements ITicketDAO {
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Ticket save(Ticket ticket) throws Exception {
        Ticket createdTicket = ticketRepository.save(ticket);
        return createdTicket;
    }

    @Override
    public List<Ticket> fetchAll() {
        List<Ticket> allTickets = new ArrayList<>();
        Iterable<Ticket> tickets = ticketRepository.findAll();
        for (Ticket ticket : tickets) {
            allTickets.add(ticket);
        }
        return allTickets;
    }

    @Override
    public Ticket fetch(int id) {
        return ticketRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        ticketRepository.deleteById(id);
    }

}
