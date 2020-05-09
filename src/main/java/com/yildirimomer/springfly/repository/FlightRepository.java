package com.yildirimomer.springfly.repository;

import com.yildirimomer.springfly.domain.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query("select s from Flight s where name like %?1%")
    List<Flight> findByName(String name);

    @Query("select s from Flight s where code like %?1%")
    List<Flight> findByCode(String code);

}