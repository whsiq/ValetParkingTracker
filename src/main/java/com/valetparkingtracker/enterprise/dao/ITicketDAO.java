package com.valetparkingtracker.enterprise.dao;

import com.valetparkingtracker.enterprise.dto.Ticket;
import java.util.List;

public interface ITicketDAO {
    Ticket save(Ticket ticket) throws Exception;

    List<Ticket> fetchAll();

    Ticket fetch(int id);

    void delete(int id);
}
