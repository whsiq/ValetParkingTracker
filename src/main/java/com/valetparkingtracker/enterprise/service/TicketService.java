package com.valetparkingtracker.enterprise.service;

import com.valetparkingtracker.enterprise.dao.ITicketDAO;
import com.valetparkingtracker.enterprise.dto.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private ITicketDAO ticketDAO;

    public TicketService() {
    }

    public TicketService(ITicketDAO TicketDAO) {
        this.ticketDAO = TicketDAO;
    }

    @Override
    @Cacheable(value = "ticket", key = "#id")
    public Ticket fetchById(int id) {
        Ticket foundTicket = ticketDAO.fetch(id);
        return foundTicket;
    }

    @Override
    @CacheEvict(value = "ticket", key = "#id")
    public void delete(int id) throws Exception {
        ticketDAO.delete(id);
    }

    @Override
    public Ticket save(Ticket ticket) throws Exception {
        return ticketDAO.save(ticket);
    }

    @Override
    @Cacheable("tickets")
    public List<Ticket> fetchAll() {
        return ticketDAO.fetchAll();
    }
}
