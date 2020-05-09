package com.yildirimomer.springfly.repository;

import com.yildirimomer.springfly.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("select s from Customer s where name like %?1%")
    List<Customer> findByName(String name);
}