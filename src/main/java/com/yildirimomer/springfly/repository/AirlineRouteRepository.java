package com.yildirimomer.springfly.repository;

import com.yildirimomer.springfly.domain.model.AirlineRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
public interface AirlineRouteRepository extends JpaRepository<AirlineRoute,Long> {
    @Query("select s from AirlineRoute s where name like %?1%")
    List<AirlineRoute> findByName(String name);

    @Query("select s from AirlineRoute s where code like %?1%")
    List<AirlineRoute> findByCode(String code);

}