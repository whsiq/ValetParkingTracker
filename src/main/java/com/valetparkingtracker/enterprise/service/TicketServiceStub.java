package com.valetparkingtracker.enterprise.service;

import com.valetparkingtracker.enterprise.dao.ITicketDAO;
import com.valetparkingtracker.enterprise.dto.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketServiceStub implements ITicketService {
    @Autowired
    private ITicketDAO ticketDAO;

    public TicketServiceStub() {
    }

    public TicketServiceStub(ITicketDAO TicketDAO) {
        this.ticketDAO = TicketDAO;
    }

    @Override
    public Ticket fetchById(int id) {
        return ticketDAO.fetch(id);
    }

    @Override
    public void delete(int id) throws Exception {
        ticketDAO.delete(id);
    }

    @Override
    public Ticket save(Ticket ticket) throws Exception {
        return ticketDAO.save(ticket);
    }

    @Override
    public List<Ticket> fetchAll() {
        return ticketDAO.fetchAll();
    }
}
