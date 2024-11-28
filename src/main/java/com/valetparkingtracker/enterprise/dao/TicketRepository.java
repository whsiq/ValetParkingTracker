package com.valetparkingtracker.enterprise.dao;

import com.valetparkingtracker.enterprise.dto.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

}
