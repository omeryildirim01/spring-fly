package com.yildirimomer.springfly.repository;

import com.yildirimomer.springfly.domain.model.Flight;
import com.yildirimomer.springfly.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Query("select s from Ticket s where ticketNumber like %?1%")
    List<Ticket> findByTicketNumber(String ticketNumber);
}