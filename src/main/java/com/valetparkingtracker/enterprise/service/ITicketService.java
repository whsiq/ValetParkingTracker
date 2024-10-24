package com.valetparkingtracker.enterprise.service;

import com.valetparkingtracker.enterprise.dto.Ticket;
import java.util.List;

public interface ITicketService {
    Ticket fetchById(int id);

    void delete(int id) throws Exception;

    Ticket save(Ticket ticket) throws Exception;

    List<Ticket> fetchAll();
}
