package com.yildirimomer.springfly.repository;

import com.yildirimomer.springfly.domain.model.Airport;
import com.yildirimomer.springfly.domain.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */

public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByFlightId(Long id);
}