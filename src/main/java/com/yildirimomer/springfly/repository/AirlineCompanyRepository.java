package com.yildirimomer.springfly.repository;

import com.yildirimomer.springfly.domain.model.AirlineCompany;
import com.yildirimomer.springfly.domain.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Omer YILDIRIM on 8.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany,Long> {
    @Query("select s from AirlineCompany s where name like %?1%")
    List<AirlineCompany> findByName(String name);
}